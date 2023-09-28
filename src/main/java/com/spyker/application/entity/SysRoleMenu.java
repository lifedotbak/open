package com.spyker.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_role_menu")
@Schema(name = "SysRoleMenu", description = "$!{table.comment}")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色ID")
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private String roleId;

    @Schema(description = "菜单ID")
    @TableId(value = "menu_id", type = IdType.ASSIGN_ID)
    private String menuId;
}