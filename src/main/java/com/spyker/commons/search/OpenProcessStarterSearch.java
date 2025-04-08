package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 流程发起人范围查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenProcessStarterSearch对象", description = "流程发起人范围Search对象")
public class OpenProcessStarterSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "用户id或者部门id")
    private String typeId;

    @Schema(description = "是否包含下级部门")
    private Boolean containChildrenDept;

    @Schema(description = " 类型 user dept")
    private String typeName;

    @Schema(description = "流程表id")
    private Long processId;

    @Schema(description = "数据")
    private String data;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "流程id")
    private String flowId;
}
