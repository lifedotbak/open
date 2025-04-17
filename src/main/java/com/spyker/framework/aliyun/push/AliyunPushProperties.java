package com.spyker.framework.aliyun.push;

import lombok.Data;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** 阿里云基础配置信息 */
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(value = "aliyun.push", ignoreInvalidFields = true)
@ConditionalOnProperty(
        prefix = "aliyun.push",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true)
public class AliyunPushProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
    private long androidKey;
    private long iosKey;
}
