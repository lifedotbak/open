package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenRoleSearch对象", description = "角色Search对象")
public class OpenRoleSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "角色名字")
    private String name;

    @Schema(description = "创建人")
    private String userId;

    @Schema(description = "")
    private String roleKey;

    @Schema(description = "")
    private Integer status;

    @Schema(description = "租户id")
    private String tenantId;
}