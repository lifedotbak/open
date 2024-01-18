package com.spyker.framework.properties;

import lombok.Data;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 注意ConfigurationProperties，prefix已经用过，不能在重复使用
 */
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "zlmediakit", ignoreUnknownFields = true)
public class ZLMediaKitCofigProperties {

    private String ip;

    private int port;

    private String secret;
}