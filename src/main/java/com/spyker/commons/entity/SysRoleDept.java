package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色和部门关联表
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@TableName("sys_role_dept")
@Schema(name = "SysRoleDept", description = "角色和部门关联表对象")
public class SysRoleDept {

    @Schema(description = "角色ID")
    private String roleId;

    @Schema(description = "部门ID")
    private String deptId;
}