package com.spyker.framework.third.hikvision;

import com.spyker.framework.third.hikvision.data.HCLoginInfo;
import com.spyker.framework.third.hikvision.data.HCOpInfo;
import com.spyker.framework.third.hikvision.exception.FExceptionCallBackImpl;
import com.spyker.framework.third.hikvision.jna.HCNetSDK;
import com.spyker.framework.third.hikvision.jna.PlayCtrl;
import com.spyker.framework.third.hikvision.utils.OsSelect;
import com.spyker.framework.third.hikvision.video.HCControl;
import com.spyker.framework.third.hikvision.video.HCVideo;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.scheduling.annotation.Async;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@ConditionalOnClass(HCProperties.class)
@AutoConfiguration
@Slf4j
public class HCUtils {

    @Autowired private HCProperties hCProperties;
    private HCNetSDK hCNetSDK;
    private PlayCtrl playControl;

    /**
     * 抓图
     *
     * @param loginDeviceInfo 设备登录信息
     * @return 图片地址
     */
    public String NET_DVR_CaptureJPEGPicture(HCLoginInfo loginDeviceInfo) {

        HCOpInfo loginSuccess = login_V40(loginDeviceInfo);

        HCControl hcControl = new HCControl(hCNetSDK, loginSuccess);

        String now = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");

        String realPath =
                hCProperties.getSystemPic()
                        + File.separator
                        + loginDeviceInfo.getIp()
                        + "_"
                        + loginDeviceInfo.getLDChannel()
                        + "_"
                        + now
                        + ".jpg";

        hcControl.NET_DVR_CaptureJPEGPicture(realPath);

        logout(loginSuccess.getLUserID());

        return realPath;
    }

    /**
     * 设备登录V40 与V30功能一致
     *
     * @param loginDeviceInfo
     * @return
     */
    private HCOpInfo login_V40(HCLoginInfo loginDeviceInfo) {

        String ip = loginDeviceInfo.getIp();
        short port = (short) loginDeviceInfo.getPort();

        String user = loginDeviceInfo.getName();
        String psw = loginDeviceInfo.getPassword();

        HCOpInfo result = new HCOpInfo();

        int lUserID = -1; // 用户句柄

        // 注册
        HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo =
                new HCNetSDK.NET_DVR_USER_LOGIN_INFO(); // 设备登录信息
        HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo =
                new HCNetSDK.NET_DVR_DEVICEINFO_V40(); // 设备信息

        String m_sDeviceIP = ip; // 设备ip地址
        m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(
                m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());

        String m_sUsername = user; // 设备用户名
        m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(
                m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());

        String m_sPassword = psw; // 设备密码
        m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(
                m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());

        m_strLoginInfo.wPort = port;
        m_strLoginInfo.bUseAsynLogin = false; // 是否异步登录：0- 否，1- 是
        m_strLoginInfo.byLoginMode = 0; // ISAPI登录
        m_strLoginInfo.write();

        lUserID = hCNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);
        if (lUserID == -1) {

            log.error("登录失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());

        } else {

            log.info("设备登录成功--->{}", ip);

            log.info("预览起始通道号--->{}", m_strDeviceInfo.struDeviceV30.byStartDChan);

            // 相机一般只有一个通道号，热成像相机有2个通道号，通道号为1或1,2
            // byStartDChan为IP通道起始通道号, 预览回放NVR的IP通道时需要根据起始通道号进行取值
            //			if (m_strDeviceInfo.struDeviceV30.byStartDChan == 1 &&
            // m_strDeviceInfo.struDeviceV30
            //			.byStartDChan == 33) {
            //				// byStartDChan为IP通道起始通道号, 预览回放NVR的IP通道时需要根据起始通道号进行取值,NVR起始通道号一般是33或者1开始
            //				lDChannel = m_strDeviceInfo.struDeviceV30.byStartDChan;
            //
            //				log.info("预览起始通道号：" + lDChannel);
            //			}

        }

        result.setLDChannel(loginDeviceInfo.getLDChannel());
        result.setLUserID(lUserID);

        return result;
    }

    private void logout(int lUserID) {

        // 退出程序时调用，每一台设备分别注销
        if (hCNetSDK.NET_DVR_Logout(lUserID)) {

            log.info("注销成功--->{}", lUserID);
        }
    }

    /**
     * 控制摄像头左转
     *
     * @param loginDeviceInfo
     */
    public void controlLeft(HCLoginInfo loginDeviceInfo) {

        HCOpInfo hCOpInfo = login_V40(loginDeviceInfo);

        log.info("hCOpInfo---->{}", hCOpInfo);

        HCControl hcControl = new HCControl(hCNetSDK, hCOpInfo);

        // 控制摄像头左转
        hcControl.controlLeft();

        logout(hCOpInfo.getLUserID());
    }

    /**
     * @param hCLoginInfo 设备登录信息
     * @param lDChannel 通道号
     * @param fileName 生成的文件名
     * @param start 开始下载时间
     * @param end 结束下载时间
     */
    @Async
    public void downloadVideoRecordByTime(
            HCLoginInfo hCLoginInfo, int lDChannel, String fileName, Date start, Date end) {

        HCOpInfo hCOpInfo = login_V40(hCLoginInfo);

        log.info("loginSuccess---->{}", hCOpInfo);

        HCVideo hcVideo = new HCVideo(hCNetSDK, playControl, hCOpInfo);

        hCOpInfo.setLDChannel(lDChannel);

        // 获取通道
        hcVideo.downloadRecordByTime(fileName, start, end);

        logout(hCOpInfo.getLUserID());
    }

    /**
     * 判断设备是否包含录像文件
     *
     * @param loginDeviceInfo
     * @param iChannelNo
     * @return
     */
    public boolean findExistVideoFile(
            HCLoginInfo loginDeviceInfo, int iChannelNo, Date start, Date end) {

        HCOpInfo loginSuccess = login_V40(loginDeviceInfo);

        HCVideo hcVideo = new HCVideo(hCNetSDK, playControl, loginSuccess);

        // 实时取流
        boolean result = hcVideo.findExistVideoFile(37, start, end);

        logout(loginSuccess.getLUserID());

        return result;
    }

    /**
     * 获取通道
     *
     * @param loginDeviceInfo
     */
    public void getDvrIPChannelInfo(HCLoginInfo loginDeviceInfo) {

        HCOpInfo loginSuccess = login_V40(loginDeviceInfo);

        log.info("loginSuccess---->{}", loginSuccess);

        HCVideo hcVideo = new HCVideo(hCNetSDK, playControl, loginSuccess);

        // 获取通道
        hcVideo.getDvrIPChannelInfo(loginSuccess.getLUserID());

        logout(loginSuccess.getLUserID());
    }

    /**
     * 抓图
     *
     * @param loginDeviceInfo
     */
    public String getPicByPlayCtrl(HCLoginInfo loginDeviceInfo) {

        HCOpInfo loginSuccess = login_V40(loginDeviceInfo);

        HCVideo hcVideo = new HCVideo(hCNetSDK, playControl, loginSuccess);

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newName = sf.format(new Date());

        String realPath =
                hCProperties.getSystemPic()
                        + File.separator
                        + loginDeviceInfo.getIp()
                        + "_"
                        + loginDeviceInfo.getLDChannel()
                        + "_"
                        + newName
                        + ".jpg";
        // 实时取流
        realPath = hcVideo.getPicByPlayCtrl(realPath);

        logout(loginSuccess.getLUserID());

        return realPath;
    }

    /**
     * 云台预置点操作
     *
     * @param hCLoginInfo
     * @param dwPresetIndex
     */
    public void gotoPreset(HCLoginInfo hCLoginInfo, int dwPresetIndex) {

        HCOpInfo hCOpInfo = login_V40(hCLoginInfo);

        HCControl hcControl = new HCControl(hCNetSDK, hCOpInfo);

        // 实时取流
        hcControl.gotoPreset(dwPresetIndex);

        logout(hCOpInfo.getLUserID());
    }

    /**
     * 摄像头转移到固定预置点，并拍照
     *
     * @param loginDeviceInfo
     * @param dwPresetIndex
     */
    public String gotoPresetAndCaptureJPEGPicture(HCLoginInfo loginDeviceInfo, int dwPresetIndex) {

        String result = "";

        HCOpInfo loginSuccess = login_V40(loginDeviceInfo);

        HCControl hcControl = new HCControl(hCNetSDK, loginSuccess);

        /** 海康摄像头，预置点 1--300。 */
        if (dwPresetIndex > 0 && dwPresetIndex < 300) {
            boolean opGotoPreset = hcControl.gotoPreset(dwPresetIndex);

            if (!opGotoPreset) {
                return result;
            }
        }

        String now = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");

        String realPath =
                hCProperties.getSystemPic()
                        + File.separator
                        + loginDeviceInfo.getIp()
                        + "_C"
                        + loginDeviceInfo.getLDChannel()
                        + "_P"
                        + dwPresetIndex
                        + "_"
                        + now
                        + ".jpg";

        boolean opCapturePic = hcControl.NET_DVR_CaptureJPEGPicture(realPath);

        logout(loginSuccess.getLUserID());

        if (opCapturePic) {
            result = realPath;
        }

        return result;
    }

    /** 系统关闭执行cleanUp代码 */
    @PreDestroy
    public void cleanUp() {

        if (null != hCNetSDK) {
            // SDK反初始化，释放资源，只需要退出时调用一次
            hCNetSDK.NET_DVR_Cleanup();
        }
    }

    /** 系统启动执行init代码 */
    @PostConstruct
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

    public void playBackBytime(HCLoginInfo hCLoginInfo, String fileName, Date start, Date end) {

        HCOpInfo hCOpInfo = login_V40(hCLoginInfo);

        log.info("loginSuccess---->{}", hCOpInfo);

        HCVideo hcVideo = new HCVideo(hCNetSDK, playControl, hCOpInfo);

        // 获取通道
        hcVideo.playBackBytime(fileName, start, end);

        logout(hCOpInfo.getLUserID());
    }

    /**
     * 获取实时流
     *
     * @param loginDeviceInfo
     */
    public void realPlay(HCLoginInfo loginDeviceInfo) {

        HCOpInfo loginSuccess = login_V40(loginDeviceInfo);

        HCVideo hcVideo = new HCVideo(hCNetSDK, playControl, loginSuccess);

        // 实时取流
        hcVideo.realPlay("D:\\testxxx.mp4");

        logout(loginSuccess.getLUserID());
    }
}