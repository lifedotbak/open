package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenProcessGroupSearch对象", description = "Search对象")
public class OpenProcessGroupSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "分组名")
    private String groupName;

    @Schema(description = "排序")
    private Integer sortValue;

    @Schema(description = "租户id")
    private String tenantId;
}