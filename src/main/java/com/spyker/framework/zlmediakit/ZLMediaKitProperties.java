package com.spyker.framework.zlmediakit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "zlmediakit", ignoreUnknownFields = true)
public class ZLMediaKitProperties {

    private String ip;

    private int port;

    private String secret;

}