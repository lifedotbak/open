package com.spyker.framework.oss.properties;

import lombok.Data;

@Data
public class OssProperties {

    /** 域名 */
    private String endpoint;

    /** 自定义域名 */
    private String domain;

    /** 前缀 */
    private String prefix;

    /** ACCESS_KEY */
    private String accessKey;

    /** SECRET_KEY */
    private String secretKey;

    /** 存储空间名 */
    private String bucketName;

    /** 存储区域 */
    private String region;

    /** 是否https（Y=是,N=否） */
    private String isHttps;
}