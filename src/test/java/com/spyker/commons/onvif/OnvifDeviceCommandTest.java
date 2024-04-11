package com.spyker.commons.onvif;

import com.spyker.BaseTest;
import com.spyker.framework.onvif.bak.OnvifDeviceCommand;
import com.spyker.framework.onvif.bak.entity.OnvifDeviceExtend;

import lombok.extern.slf4j.Slf4j;

import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class OnvifDeviceCommandTest extends BaseTest {

    @Autowired OnvifDeviceCommand onvifDeviceCommand;

    @Test
    void getSnap() {
        OnvifDeviceExtend info = new OnvifDeviceExtend("192.168.15.88", 80, "test", "grid123456");

        onvifDeviceCommand.getSnap(info, "d:/a/r/c/11.jpg");
    }

    @Test
    void gotoHome() {

        onvifDeviceCommand.ptz_goto_home(
                new OnvifDeviceExtend("192.168.15.88", 80, "test", "grid123456"));
    }

    @Test
    void getPresets() {

        onvifDeviceCommand.ptz_get_presets(
                new OnvifDeviceExtend("192.168.15.88", 80, "test", "grid123456"));
    }

    @Test
    void getSnapShotUri() {

        OnvifDeviceExtend info = new OnvifDeviceExtend("192.168.15.88", 80, "test", "grid123456");

        String result = onvifDeviceCommand.mdeia_get_snap_shot_uri(info);

        log.info("result: {}", result);
    }

    @Test
    void getStreamUri() {

        String result =
                onvifDeviceCommand.stream(
                        new OnvifDeviceExtend("192.168.15.88", 80, "test", "grid123456"));
        log.info("result: {}", result);
    }

    @Test
    void ptz() {

        OnvifDeviceExtend info = new OnvifDeviceExtend("192.168.15.88", 80, "test", "grid123456");

        //        sendOnvifCmd.ptz("admin", "grid123456", "192.168.15.88", 80, 0.5, 0, 0);

        onvifDeviceCommand.ptz_down(info);

        try {
            Thread.sleep(1000 * 3); // 休眠3秒
        } catch (Exception e) {
            System.out.println("Got an exception!");
        }

        onvifDeviceCommand.ptz_stop(info);
    }

    @Test
    void gotoPreset() {

        onvifDeviceCommand.ptz_goto_preset(
                new OnvifDeviceExtend("192.168.15.88", 80, "admin", "grid123456"), "1");
    }

    @Test
    void token() {

        String xml =
                onvifDeviceCommand.token(
                        new OnvifDeviceExtend("192.168.15.88", 80, "admin", "grid123456"));

        log.info("xml: {}", xml);
    }

    @Test
    void getReplayStreamUri() throws DocumentException {
        boolean xml =
                onvifDeviceCommand.replay_get_service_capabilities(
                        new OnvifDeviceExtend("192.168.15.88", 80, "admin", "grid123456"));

        log.info("xml: {}", xml);
    }

    @Test
    void credential_get_service_capabilities() throws DocumentException {
        onvifDeviceCommand.credential_get_service_capabilities(
                new OnvifDeviceExtend("192.168.15.88", 80, "admin", "grid123456"));
    }
}