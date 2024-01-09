package com.spyker.framework.oss.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spyker.framework.oss.constant.CacheNames;
import com.spyker.framework.oss.constant.OssConstant;
import com.spyker.framework.oss.entity.SysOssConfig;
import com.spyker.framework.oss.factory.OssFactory;
import com.spyker.framework.util.CacheUtils;
import com.spyker.framework.util.ExJsonUtils;
import com.spyker.framework.util.RedisUtils;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class OssInitConfig {

    @PostConstruct
    public void init() {

        log.info("====OssInitConfig Init====");

        SysOssConfig config = new SysOssConfig();

        config.setStatus("0");
        config.setConfigKey("minio");
        config.setAccessKey("e0Ui4uY4FBetHELN");
        config.setSecretKey("ynQa9ZhOMH6mJ7Q8JPRAdmAVqzgKFmp6");
        config.setBucketName("grid-cloud");
        config.setEndpoint("192.168.200.65:29000");

        String configKey = config.getConfigKey();
        if ("0".equals(config.getStatus())) {
            RedisUtils.setCacheObject(OssConstant.DEFAULT_CONFIG_KEY, configKey);
        }
        setConfigCache(true, config);

        OssFactory.init();
    }

    private boolean setConfigCache(boolean flag, SysOssConfig config) {
        if (flag) {
            CacheUtils.put(
                    CacheNames.SYS_OSS_CONFIG,
                    config.getConfigKey(),
                    ExJsonUtils.toJsonString(config));
            RedisUtils.publish(
                    OssConstant.DEFAULT_CONFIG_KEY,
                    config.getConfigKey(),
                    msg -> {
                        log.info("发布刷新OSS配置 => " + msg);
                    });
        }
        return flag;
    }
}