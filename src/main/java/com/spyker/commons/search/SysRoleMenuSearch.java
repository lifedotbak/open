package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色和菜单关联表查询类
 *
 * @author CodeGenerator
 * @since 2024-07-23
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

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "租户id")
    private String tenantId;
}