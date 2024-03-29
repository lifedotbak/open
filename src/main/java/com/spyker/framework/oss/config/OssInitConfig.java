package com.spyker.framework.oss.config;

import com.spyker.framework.oss.constant.OssConstant;
import com.spyker.framework.oss.entity.SysOssConfig;
import com.spyker.framework.oss.factory.OssFactory;
import com.spyker.framework.redis.RedissonService;
import com.spyker.framework.util.CacheUtils;
import com.spyker.framework.util.ExJsonUtils;

import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class OssInitConfig {

    private final OssFactory ossFactory;

    private final RedissonService redissonService;

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
            redissonService.setCacheObject(OssConstant.DEFAULT_CONFIG_KEY, configKey);
        }
        setConfigCache(true, config);

        ossFactory.init();
    }

    private boolean setConfigCache(boolean flag, SysOssConfig config) {
        if (flag) {
            CacheUtils.put(
                    OssConstant.SYS_OSS_CONFIG_KEY,
                    config.getConfigKey(),
                    ExJsonUtils.toJsonString(config));
            redissonService.publish(
                    OssConstant.DEFAULT_CONFIG_KEY,
                    config.getConfigKey(),
                    msg -> {
                        log.info("发布刷新OSS配置 => " + msg);
                        log.info("发布刷新OSS配置详情 =>{} ", config);
                    });
        }
        return flag;
    }
}