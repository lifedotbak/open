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
 * 部门表
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@TableName("open_dept")
@Schema(name = "OpenDept", description = "部门表对象")
public class OpenDept {

    @Schema(description = "部门id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "部门名")
    private String name;

    @Schema(description = "上级部门id")
    private Long parentId;

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

    private Integer status;

    private Integer sortValue;

    @Schema(description = "租户id")
    private String tenantId;
}