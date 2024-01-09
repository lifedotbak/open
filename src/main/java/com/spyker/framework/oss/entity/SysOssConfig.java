package com.spyker.framework.oss.entity;

import lombok.Data;

@Data
public class SysOssConfig {

    /** 主建 */
    private String ossConfigId;

    /** 配置key */
    private String configKey;

    /** accessKey */
    private String accessKey;

    /** 秘钥 */
    private String secretKey;

    /** 桶名称 */
    private String bucketName;

    /** 前缀 */
    private String prefix;

    /** 访问站点 */
    private String endpoint;

    /** 自定义域名 */
    private String domain;

    /** 是否https（0否 1是） */
    private String isHttps;

    /** 域 */
    private String region;

    /** 状态(0正常 1停用) */
    private String status;

    /** 扩展字段 */
    private String ext1;

    /** 备注 */
    private String remark;

    /** 桶权限类型(0private 1public 2custom) */
    private String accessPolicy;
}