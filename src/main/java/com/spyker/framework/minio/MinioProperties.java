package com.spyker.framework.minio;

import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "minio")
@AutoConfiguration
@ConditionalOnProperty(prefix = "minio", name = "enabled", havingValue = "true")
public class MinioProperties {

    private String accesskey;

    private String secretkey;

    private String endpoint;

    private String bucket;
}