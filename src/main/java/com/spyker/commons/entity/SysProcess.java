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

/** */
@RedisHash
@Data
@Accessors(chain = true)
@TableName("sys_process")
@Schema(name = "SysProcess", description = "对象")
public class SysProcess {

    @Schema(description = "PK")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "表单ID")
    private String flowId;

    @Schema(description = "表单名称")
    private String name;

    @Schema(description = "图标配置")
    private String logo;

    @Schema(description = "设置项")
    private String settings;

    @Schema(description = "分组ID")
    private String groupId;

    @Schema(description = "表单设置内容")
    private String formItems;

    @Schema(description = "流程设置内容")
    private String process;

    @Schema(description = "备注")
    private String remark;

    private Integer processSort;

    @Schema(description = "0 正常 1=隐藏")
    private Integer isHidden;

    @Schema(description = "0 正常 1=停用 ")
    private Integer isStop;

    @Schema(description = "流程管理员")
    private String adminId;

    @Schema(description = "唯一性id")
    private String uniqueId;

    @Schema(description = "管理员")
    private String admin;

    @Schema(description = "范围描述显示")
    private String rangeShow;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "表单设置内容pc")
    private String formItemsPc;
}
