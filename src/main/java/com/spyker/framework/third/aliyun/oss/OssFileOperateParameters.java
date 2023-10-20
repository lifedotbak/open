package com.spyker.framework.third.aliyun.oss;

import lombok.Data;

import java.io.InputStream;

@Data
public class OssFileOperateParameters {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String objectName;
    private InputStream uploadFileInputStream;
    private String download2FilePath;

}