package com.spyker.framework.zlmediakit;

import lombok.Data;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AutoConfiguration
@ConfigurationProperties(prefix = "zlmediakit", ignoreUnknownFields = true)
@ConditionalOnProperty(prefix = "zlmediakit", name = "enabled", havingValue = "true")
public class ZLMediaKitProperties {

    private String ip;

    private int port;

    private String secret;
}