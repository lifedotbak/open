package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 部门-主管表查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "SysDeptLeaderSearch对象", description = "部门-主管表Search对象")
public class SysDeptLeaderSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "部门id")
    private String deptId;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "主管user_id")
    private String userId;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "租户id")
    private String tenantId;
}
