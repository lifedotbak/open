package com.spyker.framework.aliyun.oss;

import java.io.InputStream;

import lombok.Data;

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
