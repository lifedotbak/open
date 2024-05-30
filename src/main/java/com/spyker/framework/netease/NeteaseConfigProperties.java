package com.spyker.framework.netease;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(value = "netease", ignoreInvalidFields = true)
public class NeteaseConfigProperties {

    private Im im;

    @Data
    private class Im {

        private String appKey;
        private String appSecret;
    }
}