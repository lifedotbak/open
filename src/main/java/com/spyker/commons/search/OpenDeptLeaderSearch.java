package com.spyker.commons.search;

// @formatter:off

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 部门-主管表查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenDeptLeaderSearch对象", description = "部门-主管表Search对象")
public class OpenDeptLeaderSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "部门id")
    private String deptId;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "主管user_id")
    private String userId;

    @Schema(description = "租户id")
    private String tenantId;
}