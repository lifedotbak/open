package com.spyker.framework.aliyun.oss;

import lombok.Data;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** 阿里云基础配置信息 */
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(value = "aliyun.ram", ignoreInvalidFields = true)
@ConditionalOnProperty(
        prefix = "aliyun.ram",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true)
public class AliyunRamProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String roleArn;
    private String roleSessionName;
    private int durationSeconds;
}
