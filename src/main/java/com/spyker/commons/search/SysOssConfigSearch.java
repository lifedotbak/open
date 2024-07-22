package com.spyker.commons.search;

// @formatter:off

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 对象存储配置表查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysOssConfigSearch对象", description = "对象存储配置表Search对象")
public class SysOssConfigSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "主建")
    private String ossConfigId;

    @Schema(description = "配置key")
    private String configKey;

    @Schema(description = "accessKey")
    private String accessKey;

    @Schema(description = "秘钥")
    private String secretKey;

    @Schema(description = "桶名称")
    private String bucketName;

    @Schema(description = "前缀")
    private String prefix;

    @Schema(description = "访问站点")
    private String endpoint;

    @Schema(description = "自定义域名")
    private String domain;

    @Schema(description = "是否https（Y=是,N=否）")
    private String isHttps;

    @Schema(description = "域")
    private String region;

    @Schema(description = "桶权限类型(0=private 1=public 2=custom)")
    private String accessPolicy;

    @Schema(description = "状态（0=正常,1=停用）")
    private String status;

    @Schema(description = "扩展字段")
    private String ext1;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "备注")
    private String remark;
}