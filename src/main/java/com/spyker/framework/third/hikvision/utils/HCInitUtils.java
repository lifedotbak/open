package com.spyker.framework.third.hikvision.utils;

import com.spyker.framework.third.hikvision.HCProperties;
import com.spyker.framework.third.hikvision.exception.FExceptionCallBackImpl;
import com.spyker.framework.third.hikvision.jna.HCNetSDK;
import com.spyker.framework.third.hikvision.jna.PlayCtrl;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

@AutoConfiguration
@ConditionalOnClass(HCProperties.class)
@Configurable
@Slf4j
public class HCInitUtils {

    @Autowired private HCProperties hCProperties;

    private HCNetSDK hCNetSDK;

    private PlayCtrl playControl;

    public HCNetSDK getHCNetSDK() {
        init();
        return hCNetSDK;
    }

    /** 系统启动执行init代码 */
    //    @PostConstruct
    public void init() {

        if (hCNetSDK == null && playControl == null) {
            if (!createSDKInstance()) {

                log.error("Load SDK fail");

                return;
            }
            if (!createPlayInstance()) {

                log.error("Load PlayCtrl fail");
                return;
            }
        }
        // linux系统建议调用以下接口加载组件库
        if (OsSelect.isLinux()) {
            HCNetSDK.BYTE_ARRAY ptrByteArray1 = new HCNetSDK.BYTE_ARRAY(256);
            HCNetSDK.BYTE_ARRAY ptrByteArray2 = new HCNetSDK.BYTE_ARRAY(256);
            // 这里是库的绝对路径，请根据实际情况修改，注意改路径必须有访问权限
            String strPath1 = hCProperties.getSystemLib() + "/libcrypto.so.1.1";
            String strPath2 = hCProperties.getSystemLib() + "/libssl.so.1.1";

            log.info("lib so path ---->{},{}", strPath1, strPath2);

            System.arraycopy(strPath1.getBytes(), 0, ptrByteArray1.byValue, 0, strPath1.length());
            ptrByteArray1.write();
            hCNetSDK.NET_DVR_SetSDKInitCfg(3, ptrByteArray1.getPointer());

            System.arraycopy(strPath2.getBytes(), 0, ptrByteArray2.byValue, 0, strPath2.length());
            ptrByteArray2.write();
            hCNetSDK.NET_DVR_SetSDKInitCfg(4, ptrByteArray2.getPointer());

            String strPathCom = hCProperties.getSystemLib() + "/";
            log.info("strPathCom path ---->{}", strPathCom);

            HCNetSDK.NET_DVR_LOCAL_SDK_PATH struComPath = new HCNetSDK.NET_DVR_LOCAL_SDK_PATH();
            System.arraycopy(strPathCom.getBytes(), 0, struComPath.sPath, 0, strPathCom.length());
            struComPath.write();
            hCNetSDK.NET_DVR_SetSDKInitCfg(2, struComPath.getPointer());
        }

        // SDK初始化，一个程序只需要调用一次
        hCNetSDK.NET_DVR_Init();

        // 异常消息回调
        FExceptionCallBackImpl fExceptionCallBack = new FExceptionCallBackImpl();

        if (!hCNetSDK.NET_DVR_SetExceptionCallBack_V30(0, 0, fExceptionCallBack, Pointer.NULL)) {
            return;
        }

        log.info("设置异常消息回调成功");

        // 启动SDK写日志
        hCNetSDK.NET_DVR_SetLogToFile(3, "./sdkLog", false);
    }

    /**
     * 动态库加载
     *
     * @return
     */
    private boolean createSDKInstance() {
        if (hCNetSDK == null) {
            synchronized (HCNetSDK.class) {
                String strDllPath = "";
                try {
                    if (OsSelect.isWindows()) {
                        // win系统加载库路径
                        strDllPath = hCProperties.getSystemLib() + "\\HCNetSDK.dll";
                    } else {
                        if (OsSelect.isLinux()) {
                            // Linux系统加载库路径
                            strDllPath = hCProperties.getSystemLib() + "/libhcnetsdk.so";
                        }
                    }
                    hCNetSDK = (HCNetSDK) Native.loadLibrary(strDllPath, HCNetSDK.class);
                } catch (Exception ex) {
                    System.out.println("loadLibrary: " + strDllPath + " Error: " + ex.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 播放库加载
     *
     * @return
     */
    private boolean createPlayInstance() {
        if (playControl == null) {
            synchronized (PlayCtrl.class) {
                String strPlayPath = "";
                try {
                    if (OsSelect.isWindows()) {
                        // win系统加载库路径
                        strPlayPath = hCProperties.getSystemLib() + "\\PlayCtrl.dll";
                    } else {
                        if (OsSelect.isLinux()) {
                            // Linux系统加载库路径

                            strPlayPath = hCProperties.getSystemLib() + "/libPlayCtrl.so";
                        }
                    }
                    playControl = (PlayCtrl) Native.loadLibrary(strPlayPath, PlayCtrl.class);

                } catch (Exception ex) {
                    System.out.println(
                            "loadLibrary: " + strPlayPath + " Error: " + ex.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    public PlayCtrl getPlayCtrl() {

        init();

        return playControl;
    }

    /** 系统关闭执行cleanUp代码 */
    //    @PreDestroy
    public void cleanUp() {

        if (null != hCNetSDK) {
            // SDK反初始化，释放资源，只需要退出时调用一次
            hCNetSDK.NET_DVR_Cleanup();
        }
    }
}