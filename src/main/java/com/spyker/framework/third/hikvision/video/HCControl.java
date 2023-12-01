package com.spyker.framework.third.hikvision.video;

import com.spyker.framework.third.hikvision.data.HCOpInfo;
import com.spyker.framework.third.hikvision.jna.HCNetSDK;
import lombok.extern.slf4j.Slf4j;

/**
 * 设备控制类
 *
 * @author spyker
 */
@Slf4j
public class HCControl {

    private final HCNetSDK hCNetSDK;

    private final HCOpInfo loginSuccess;

    public HCControl(HCNetSDK hCNetSDK, HCOpInfo loginSuccess) {
        this.hCNetSDK = hCNetSDK;
        this.loginSuccess = loginSuccess;
    }

    public boolean NET_DVR_CaptureJPEGPicture(String realPicPath) {

        log.info("realPicPath-->{}", realPicPath);

        HCNetSDK.NET_DVR_JPEGPARA net_dvr_jpegpara = new HCNetSDK.NET_DVR_JPEGPARA();

        net_dvr_jpegpara.wPicSize = 3;
        net_dvr_jpegpara.wPicQuality = 255;

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
    public boolean gotoPreset(int dwPresetIndex) {

        boolean opFlag = hCNetSDK.NET_DVR_PTZPreset_Other(loginSuccess.getLUserID(),
                                                          loginSuccess.getLDChannel(),
                                                          HCNetSDK.GOTO_PRESET,
                                                          dwPresetIndex);

        if (!opFlag) {
            log.error("NET_DVR_PTZPreset_Other 操作失败：" + hCNetSDK.NET_DVR_GetLastError());
        }

        return opFlag;
    }

    public void controlLeft() {

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

    public void controlRight() {

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

    public void controlUp() {

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

    public void controlDown() {

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