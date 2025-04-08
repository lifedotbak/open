package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 部门表查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenDeptSearch对象", description = "部门表Search对象")
public class OpenDeptSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "部门名")
    private String name;

    @Schema(description = "上级部门id")
    private Long parentId;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "")
    private Integer status;

    @Schema(description = "")
    private Integer sortValue;

    @Schema(description = "租户id")
    private String tenantId;
}
