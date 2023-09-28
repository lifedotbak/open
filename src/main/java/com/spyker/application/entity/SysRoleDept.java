package com.spyker.application.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色和部门关联表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_role_dept")
@Schema(name = "SysRoleDept", description = "$!{table.comment}")
public class SysRoleDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色ID")
    private String roleId;

    @Schema(description = "部门ID")
    private String deptId;
}