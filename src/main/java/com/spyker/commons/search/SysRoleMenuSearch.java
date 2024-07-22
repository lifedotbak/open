package com.spyker.commons.search;

// @formatter:off

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色和菜单关联表查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysRoleMenuSearch对象", description = "角色和菜单关联表Search对象")
public class SysRoleMenuSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "角色ID")
    private String roleId;

    @Schema(description = "菜单ID")
    private String menuId;
}