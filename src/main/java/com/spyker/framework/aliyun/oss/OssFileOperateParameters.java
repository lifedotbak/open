package com.spyker.framework.aliyun.oss;

import lombok.Data;

import java.io.InputStream;

@Data
public class OssFileOperateParameters {

    private String bucketName;
    private String objectName;
    private InputStream uploadFileInputStream;
    private String download2FilePath;
}
