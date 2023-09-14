package com.spyker.framework.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(MinioProperties.class)
@AutoConfiguration
public class MinioConfig {

    @Autowired
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        MinioClient client =
                MinioClient.builder().endpoint(minioProperties.getEndpoint()).credentials(minioProperties.getAccesskey(), minioProperties.getSecretkey()).build();
        return client;
    }
}