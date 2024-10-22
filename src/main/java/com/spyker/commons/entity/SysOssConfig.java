package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

/**
 * 对象存储配置表
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@TableName("sys_oss_config")
@Schema(name = "SysOssConfig", description = "对象存储配置表对象")
@RedisHash
public class SysOssConfig {

    @Schema(description = "主建")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

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
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "备注")
    private String remark;
}