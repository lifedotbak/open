package com.spyker.commons.search;

// @formatter:off

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流程主表查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenProcessMainSearch对象", description = "流程主表Search对象")
public class OpenProcessMainSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "表单名称")
    private String name;

    @Schema(description = "图标配置")
    private String logo;

    @Schema(description = "分组ID")
    private Long groupId;

    @Schema(description = "")
    private Integer sortValue;

    @Schema(description = "唯一性id")
    private String uniqueId;

    @Schema(description = "范围描述显示")
    private String rangeShow;

    @Schema(description = "租户id")
    private String tenantId;
}