package com.spyker.framework.minio;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.http.Method;

import jakarta.validation.constraints.NotNull;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
@AutoConfiguration
@ConditionalOnClass({MinioClient.class, MinioProperties.class})
@RequiredArgsConstructor
public class MinioUtils {

    private final MinioClient minioClient;

    private final MinioProperties minioProperties;

    public String getBucket() {
        return minioProperties.getBucket();
    }

    /**
     * minio需要配置Access Rules--->(*,readonlu),负责获取文件会有过期以及无权限问题
     *
     * <p>https://blog.csdn.net/hanjun0612/article/details/118100616
     *
     * @param fileKey
     * @return url
     */
    public String getUrlByKey(String fileKey) {

        String result = "";

        try {
            GetPresignedObjectUrlArgs getPresignedObjectUrlArgs =
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(minioProperties.getBucket())
                            .object(fileKey)
                            .build();

            result = minioClient.getPresignedObjectUrl(getPresignedObjectUrlArgs);

            log.info("url--->{}", result);
        } catch (Exception e) {
            log.error("------------>{}", e);
        }

        return result;
    }

    public boolean put(String fileKey, File file) {

        try {

            InputStream inputStream = new FileInputStream(file);

            ObjectWriteResponse objectWriteResponse =
                    minioClient.putObject(
                            PutObjectArgs.builder()
                                    .bucket(minioProperties.getBucket())
                                    .object(fileKey)
                                    .stream(inputStream, -1, 10485760)
                                    .contentType("application/octet-stream")
                                    .build());

            log.info("--->{}", objectWriteResponse.etag());

            return true;
        } catch (Exception e) {

            log.error("--->{}", e);
            return false;
        }
    }

    public boolean put(String fileKey, File file, @NotNull ContentTypeEnum contentTypeEnum) {

        try {

            InputStream inputStream = new FileInputStream(file);

            ObjectWriteResponse objectWriteResponse =
                    minioClient.putObject(
                            PutObjectArgs.builder()
                                    .bucket(minioProperties.getBucket())
                                    .object(fileKey)
                                    .stream(inputStream, -1, 10485760)
                                    .contentType(contentTypeEnum.getContent())
                                    .build());

            log.info("--->{}", objectWriteResponse.etag());

            return true;
        } catch (Exception e) {

            log.error("--->{}", e);
            return false;
        }
    }

    public boolean put(String fileKey, InputStream inputStream, String contentType) {

        try {

            ObjectWriteResponse objectWriteResponse =
                    minioClient.putObject(
                            PutObjectArgs.builder()
                                    .bucket(minioProperties.getBucket())
                                    .object(fileKey)
                                    .stream(inputStream, -1, 10485760)
                                    .contentType(contentType)
                                    .build());
            // .stream(inputStream, -1, 10485760).contentType("application/octet-stream")
            // .build());

            log.info("--->{}", objectWriteResponse.etag());

            return true;
        } catch (Exception e) {

            log.error("--->{}", e);
            return false;
        }
    }

    public boolean put(
            String fileKey, InputStream inputStream, @NotNull ContentTypeEnum contentTypeEnum) {

        try {

            ObjectWriteResponse objectWriteResponse =
                    minioClient.putObject(
                            PutObjectArgs.builder()
                                    .bucket(minioProperties.getBucket())
                                    .object(fileKey)
                                    .stream(inputStream, -1, 10485760)
                                    .contentType(contentTypeEnum.getContent())
                                    .build());
            // .stream(inputStream, -1, 10485760).contentType("application/octet-stream")
            // .build());

            log.info("--->{}", objectWriteResponse.etag());

            return true;
        } catch (Exception e) {

            log.error("--->{}", e);
            return false;
        }
    }
}