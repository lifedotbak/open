package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_role")
@Schema(name = "SysRole", description = "角色信息表")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色ID")
    @TableId(value = "role_id", type = IdType.ASSIGN_UUID)
    private String roleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色权限字符串")
    private String roleKey;

    @Schema(description = "显示顺序")
    private Integer roleSort;

    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private String dataScope;

    @Schema(description = "菜单树选择项是否关联显示")
    private Boolean menuCheckStrictly;

    @Schema(description = "部门树选择项是否关联显示")
    private Boolean deptCheckStrictly;

    @Schema(description = "角色状态（0正常 1停用）")
    private String status;

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