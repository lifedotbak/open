package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 用户-角色查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "SysUserRoleSearch对象", description = "用户-角色Search对象")
public class SysUserRoleSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "用户id")
    private String userId;

    @Schema(description = "角色id")
    private String roleId;

    @Schema(description = "租户id")
    private String tenantId;
}
