package com.spyker.framework.minio;

import io.minio.MinioClient;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(MinioProperties.class)
@AutoConfiguration
@RequiredArgsConstructor
public class MinioConfig {

    private final MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        MinioClient client =
                MinioClient.builder()
                        .endpoint(minioProperties.getEndpoint())
                        .credentials(minioProperties.getAccesskey(), minioProperties.getSecretkey())
                        .build();
        return client;
    }
}
