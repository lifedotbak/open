package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 部门表查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "SysDeptSearch对象", description = "部门表Search对象")
public class SysDeptSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "部门名")
    private String name;

    @Schema(description = "上级部门id")
    private String parentId;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "")
    private Integer status;

    @Schema(description = "")
    private Integer deptSort;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "祖级列表")
    private String ancestors;
}
