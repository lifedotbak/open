package com.spyker.commons.utils;

import com.spyker.BaseTest;
import com.spyker.framework.aliyun.oss.AliyunOssProperties;
import com.spyker.framework.aliyun.oss.AliyunRamProperties;
import com.spyker.framework.aliyun.push.AliyunPushProperties;
import com.spyker.framework.aliyun.sms.AliyunSmsProperties;
import com.spyker.framework.aliyun.voice.AliyunTtsProperties;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AliyunConfigPropertiesTest extends BaseTest {

    @Autowired private AliyunSmsProperties smsProperties;
    @Autowired private AliyunTtsProperties ttsProperties;
    @Autowired private AliyunPushProperties pushProperties;
    @Autowired private AliyunOssProperties ossProperties;
    @Autowired private AliyunRamProperties ramProperties;

    @Test
    public void aliyunConfigProperties() {
        log.info("smsProperties-->{}", smsProperties);
        log.info("ttsProperties-->{}", ttsProperties);
        log.info("pushProperties-->{}", pushProperties);
        log.info("ossProperties-->{}", ossProperties);
        log.info("ramProperties-->{}", ramProperties);
    }
}
