package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@TableName("open_process")
@Schema(name = "OpenProcess", description = "对象")
public class OpenProcess {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    // @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    // @TableField(fill = FieldFill.INSERT_UPDATE)
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
    private Long groupId;

    @Schema(description = "表单设置内容")
    private String formItems;

    @Schema(description = "流程设置内容")
    private String process;

    @Schema(description = "备注")
    private String remark;

    private Integer sortValue;

    @Schema(description = "0 正常 1=隐藏")
    private Boolean isHidden;

    @Schema(description = "0 正常 1=停用 ")
    private Boolean isStop;

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