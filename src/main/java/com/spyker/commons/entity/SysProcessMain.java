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
 * 流程主表
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@RedisHash
@Data
@Accessors(chain = true)
@TableName("sys_process_main")
@Schema(name = "SysProcessMain", description = "流程主表对象")
public class SysProcessMain {

    @Schema(description = "PK")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

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

    @Schema(description = "表单名称")
    private String name;

    @Schema(description = "图标配置")
    private String logo;

    @Schema(description = "分组ID")
    private String groupId;

    private Integer processMainSort;

    @Schema(description = "唯一性id")
    private String uniqueId;

    @Schema(description = "范围描述显示")
    private String rangeShow;

    @Schema(description = "租户id")
    private String tenantId;
}