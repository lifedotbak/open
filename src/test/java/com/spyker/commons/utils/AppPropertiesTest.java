package com.spyker.commons.utils;

import com.spyker.BaseTest;
import com.spyker.framework.aliyun.AliyunConfigProperties;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AppPropertiesTest extends BaseTest {

    @Autowired private AliyunConfigProperties aliyunConfigProperties;

    //    @Autowired private ZLMediaKitCofigProperties zLMediaKitCofigProperties;

    @Test
    public void log() {
        log.info("aliyunConfigProperties-->{}", aliyunConfigProperties);
        //        log.info("zLMediaKitCofigProperties-->{}", zLMediaKitCofigProperties);
    }
}
