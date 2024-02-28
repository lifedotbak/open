package com.spyker.framework.properties;

/** 注意ConfigurationProperties，prefix已经用过，不能在重复使用 */
// @Data
// @Configuration
// @ConfigurationProperties(prefix = "zlmediakit", ignoreUnknownFields = true)
public class ZLMediaKitCofigProperties {

    private String ip;

    private int port;

    private String secret;
}