package com.spyker.framework.third.hikvision.action;

import com.spyker.framework.third.hikvision.data.HCOpInfo;
import com.spyker.framework.third.hikvision.jna.HCNetSDK;
import com.spyker.framework.third.hikvision.utils.CommonUtil;
import com.spyker.framework.third.hikvision.utils.HCInitUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.util.Date;

/**
 * 云台设备控制
 *
 * @author spyker
 */
@AutoConfiguration
@Slf4j
@ConditionalOnClass(HCInitUtils.class)
public class HCVideoControlAction {

    @Autowired
    private HCInitUtils hCInitUtils;

    /**
     * 设置系统时间
     *
     * @param loginSuccess
     * @param time
     * @return
     */
    public boolean SET_DVR_TIME(HCOpInfo loginSuccess, Date time) {
        HCNetSDK hCNetSDK = hCInitUtils.getHCNetSDK();

        HCNetSDK.NET_DVR_TIME net_dvr_time = CommonUtil.getHkTime(time);

        net_dvr_time.write();

        /**
         *  通道号，不同的命令对应不同的取值，如果该参数无效则置为0xFFFFFFFF即可，详见“Remarks”说明
         */
        boolean op = hCNetSDK.NET_DVR_SetDVRConfig(loginSuccess.getLUserID(),
                                                   HCNetSDK.NET_DVR_SET_TIMECFG,
                                                   0xFFFFFFFF,
                                                   net_dvr_time.getPointer(),
                                                   net_dvr_time.size());

        if (!op) {
            log.error("SET_DVR_TIME 操作失败：" + hCNetSDK.NET_DVR_GetLastError());
        }

        return op;
    }

    /**
     * 抓图
     *
     * @param realPicPath
     * @return
     */
    public boolean NET_DVR_CaptureJPEGPicture(HCOpInfo loginSuccess, String realPicPath) {

        HCNetSDK hCNetSDK = hCInitUtils.getHCNetSDK();

        log.info("realPicPath-->{}", realPicPath);

        HCNetSDK.NET_DVR_JPEGPARA net_dvr_jpegpara = new HCNetSDK.NET_DVR_JPEGPARA();

        net_dvr_jpegpara.wPicSize = 3;
        net_dvr_jpegpara.wPicQuality = 0;

        boolean op = hCNetSDK.NET_DVR_CaptureJPEGPicture(loginSuccess.getLUserID(),
                                                         loginSuccess.getLDChannel(),
                                                         net_dvr_jpegpara,
                                                         realPicPath.getBytes());

        if (!op) {
            log.error("NET_DVR_CaptureJPEGPicture 操作失败：" + hCNetSDK.NET_DVR_GetLastError());
        }

        return op;

    }

    /**
     * 云台预置点操作
     *
     * @param dwPresetIndex 预置点序号
     */
    public boolean NET_DVR_PTZPreset_Other(HCOpInfo loginSuccess, int dwPresetIndex) {

        HCNetSDK hCNetSDK = hCInitUtils.getHCNetSDK();

        boolean opFlag = hCNetSDK.NET_DVR_PTZPreset_Other(loginSuccess.getLUserID(),
                                                          loginSuccess.getLDChannel(),
                                                          HCNetSDK.GOTO_PRESET,
                                                          dwPresetIndex);

        if (!opFlag) {

            log.error("NET_DVR_PTZPreset_Other 操作失败：" + hCNetSDK.NET_DVR_GetLastError());
        }

        return opFlag;
    }

    public void controlLeft(HCOpInfo loginSuccess) {

        HCNetSDK hCNetSDK = hCInitUtils.getHCNetSDK();

        /**
         * 左转
         */
        hCNetSDK.NET_DVR_PTZControl_Other(loginSuccess.getLUserID(), loginSuccess.getLDChannel(), HCNetSDK.PAN_LEFT, 0);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        /**
         * 停止转动
         */
        hCNetSDK.NET_DVR_PTZControl_Other(loginSuccess.getLUserID(), loginSuccess.getLDChannel(), HCNetSDK.PAN_LEFT, 1);

    }

    public void controlRight(HCOpInfo loginSuccess) {

        HCNetSDK hCNetSDK = hCInitUtils.getHCNetSDK();

        /**
         * 左转
         */
        hCNetSDK.NET_DVR_PTZControl_Other(loginSuccess.getLUserID(),
                                          loginSuccess.getLDChannel(),
                                          HCNetSDK.PAN_RIGHT,
                                          0);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        /**
         * 停止转动
         */
        hCNetSDK.NET_DVR_PTZControl_Other(loginSuccess.getLUserID(),
                                          loginSuccess.getLDChannel(),
                                          HCNetSDK.PAN_RIGHT,
                                          1);

    }

    public void controlUp(HCOpInfo loginSuccess) {

        HCNetSDK hCNetSDK = hCInitUtils.getHCNetSDK();

        /**
         * 左转
         */
        hCNetSDK.NET_DVR_PTZControl_Other(loginSuccess.getLUserID(), loginSuccess.getLDChannel(), HCNetSDK.TILT_UP, 0);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        /**
         * 停止转动
         */
        hCNetSDK.NET_DVR_PTZControl_Other(loginSuccess.getLUserID(), loginSuccess.getLDChannel(), HCNetSDK.TILT_UP, 1);

    }

    public void controlDown(HCOpInfo loginSuccess) {

        HCNetSDK hCNetSDK = hCInitUtils.getHCNetSDK();

        /**
         * 左转
         */
        hCNetSDK.NET_DVR_PTZControl_Other(loginSuccess.getLUserID(),
                                          loginSuccess.getLDChannel(),
                                          HCNetSDK.TILT_DOWN,
                                          0);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        /**
         * 停止转动
         */
        hCNetSDK.NET_DVR_PTZControl_Other(loginSuccess.getLUserID(),
                                          loginSuccess.getLDChannel(),
                                          HCNetSDK.TILT_DOWN,
                                          1);

    }

}