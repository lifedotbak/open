package com.spyker.commons.onvif;

import com.spyker.framework.onvif.OnvifUtils;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;

@Slf4j
class OnvifUtilsTest {

    private static final String cameraAddress = "192.168.15.225";

    @Test
    void getToken() throws Exception {

        //        String cameraAddress = "192.168.3.138";
        String user = "admin";
        String password = "123456";

        OnvifUtils utils = new OnvifUtils(cameraAddress, user, password);

        log.info(utils.getToken());
    }

    //    @Test
    void getSnapshotUri() throws Exception {

        String user = "admin";
        String password = "123456";

        OnvifUtils utils = new OnvifUtils(cameraAddress, user, password);

        log.info(utils.getSnapshotUri("mainMediaProfileToken"));
    }

    //    @Test
    void snapshot() throws Exception {

        String user = "admin";
        String password = "123456";

        OnvifUtils utils = new OnvifUtils(cameraAddress, user, password);

        utils.snapshot("mainMediaProfileToken", "d:/xxx/xxx/xx/56hghfh.jpg");
    }
}