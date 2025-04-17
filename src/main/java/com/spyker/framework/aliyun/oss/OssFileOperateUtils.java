package com.spyker.framework.aliyun.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.io.File;

@Slf4j
@AutoConfiguration
@ConditionalOnClass(AliyunOssProperties.class)
public class OssFileOperateUtils {

    @Autowired private AliyunOssProperties aliyunOssProperties;

    /**
     * 下载文件
     *
     * @param ossFileOperateParameters
     */
    public void download(OssFileOperateParameters ossFileOperateParameters) {

        if (null != ossFileOperateParameters) {

            // 创建OSSClient实例。
            OSS ossClient =
                    new OSSClientBuilder()
                            .build(
                                    aliyunOssProperties.getEndPoint(),
                                    aliyunOssProperties.getAccessKeyId(),
                                    aliyunOssProperties.getAccessKeySecret());

            // 下载文件。
            ossClient.getObject(
                    new GetObjectRequest(
                            ossFileOperateParameters.getBucketName(),
                            ossFileOperateParameters.getObjectName()),
                    new File(ossFileOperateParameters.getDownload2FilePath()));
            // 关闭Client。
            ossClient.shutdown();
        }
    }

    /**
     * 上传文件
     *
     * @param ossFileOperateParameters
     */
    public boolean upload(OssFileOperateParameters ossFileOperateParameters) {

        boolean isSuccessful = false;

        if (null != ossFileOperateParameters) {

            // 创建OSSClient实例。
            OSS ossClient =
                    new OSSClientBuilder()
                            .build(
                                    aliyunOssProperties.getEndPoint(),
                                    aliyunOssProperties.getAccessKeyId(),
                                    aliyunOssProperties.getAccessKeySecret());

            // 上传文件。
            PutObjectResult putObjectResult =
                    ossClient.putObject(
                            ossFileOperateParameters.getBucketName(),
                            ossFileOperateParameters.getObjectName(),
                            ossFileOperateParameters.getUploadFileInputStream());

            if (null != putObjectResult
                    && null != putObjectResult.getResponse()
                    && putObjectResult.getResponse().isSuccessful()) {

                log.info("putObjectResult ===> {}", putObjectResult);
                log.info("RequestId ===> {}", putObjectResult.getRequestId());

                isSuccessful = true;
            }

            // 关闭Client。
            ossClient.shutdown();
        }

        return isSuccessful;
    }

    /**
     * 删除文件
     *
     * @param ossFileOperateParameters
     */
    public void delete(OssFileOperateParameters ossFileOperateParameters) {

        if (null != ossFileOperateParameters) {

            // 创建OSSClient实例。
            OSS ossClient =
                    new OSSClientBuilder()
                            .build(
                                    aliyunOssProperties.getEndPoint(),
                                    aliyunOssProperties.getAccessKeyId(),
                                    aliyunOssProperties.getAccessKeySecret());

            try {
                // 上传文件。
                ossClient.deleteObject(
                        ossFileOperateParameters.getBucketName(),
                        ossFileOperateParameters.getObjectName());

            } catch (ClientException e) {
                log.error("Failed：");
                log.error("Error code: " + e.getErrorMessage());
                log.error("Error message: " + e.getErrorCode());
                log.error("RequestId: " + e.getRequestId());

                log.error("error -->{}", e);
            } finally {
                // 关闭Client。
                ossClient.shutdown();
            }
        }
    }
}
