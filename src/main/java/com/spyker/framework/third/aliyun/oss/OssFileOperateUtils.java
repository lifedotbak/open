package com.spyker.framework.third.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class OssFileOperateUtils {

    /**
     * 下载文件
     *
     * @param ossFileOperateParameters
     */
    public static void download(OssFileOperateParameters ossFileOperateParameters) {

        if (null != ossFileOperateParameters) {

            // 创建OSSClient实例。

            OSS ossClient = new OSSClientBuilder().build(ossFileOperateParameters.getEndpoint(),
                                                         ossFileOperateParameters.getAccessKeyId(),
                                                         ossFileOperateParameters.getAccessKeySecret());

            // 下载文件。
            ossClient.getObject(new GetObjectRequest(ossFileOperateParameters.getBucketName(),
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
    public static boolean upload(OssFileOperateParameters ossFileOperateParameters) {

        boolean isSuccessful = false;

        if (null != ossFileOperateParameters) {

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(ossFileOperateParameters.getEndpoint(),
                                                         ossFileOperateParameters.getAccessKeyId(),
                                                         ossFileOperateParameters.getAccessKeySecret());

            // 上传文件。
            PutObjectResult putObjectResult = ossClient.putObject(ossFileOperateParameters.getBucketName(),
                                                                  ossFileOperateParameters.getObjectName(),
                                                                  ossFileOperateParameters.getUploadFileInputStream());

            if (null != putObjectResult && null != putObjectResult.getResponse() && putObjectResult.getResponse()
                                                                                                   .isSuccessful()) {

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
    public static void delete(OssFileOperateParameters ossFileOperateParameters) {

        if (null != ossFileOperateParameters) {

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(ossFileOperateParameters.getEndpoint(),
                                                         ossFileOperateParameters.getAccessKeyId(),
                                                         ossFileOperateParameters.getAccessKeySecret());

            try {
                // 上传文件。
                ossClient.deleteObject(ossFileOperateParameters.getBucketName(),
                                       ossFileOperateParameters.getObjectName());
                // 关闭Client。
                ossClient.shutdown();
            } catch (Exception e) {
                log.error("error  ==>{}", e);
            }
        }

    }
}