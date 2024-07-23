package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色和部门关联表查询类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysRoleDeptSearch对象", description = "角色和部门关联表Search对象")
public class SysRoleDeptSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "角色ID")
    private String roleId;

    @Schema(description = "部门ID")
    private String deptId;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;
}