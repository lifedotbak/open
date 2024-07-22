package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色和菜单关联表
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@TableName("sys_role_menu")
@Schema(name = "SysRoleMenu", description = "角色和菜单关联表对象")
public class SysRoleMenu {

    @Schema(description = "角色ID")
    @TableId(value = "role_id", type = IdType.ASSIGN_UUID)
    private String roleId;

    @Schema(description = "菜单ID")
    @TableId(value = "menu_id", type = IdType.ASSIGN_UUID)
    private String menuId;
}