package com.spyker.framework.minio;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
@AutoConfiguration
@ConditionalOnClass({MinioClient.class, MinioProperties.class})
public class MinioUtils {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioProperties minioProperties;

    public boolean put(String fileKey, File file) {

        try {

            InputStream inputStream = new FileInputStream(file);

            ObjectWriteResponse objectWriteResponse =
                    minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucket()).object(fileKey).stream(inputStream, -1, 10485760).contentType("application/octet-stream").build());

            log.info("--->{}", objectWriteResponse.etag());

            return true;
        } catch (Exception e) {

            log.error("--->{}", e);
            return false;
        }

    }
}