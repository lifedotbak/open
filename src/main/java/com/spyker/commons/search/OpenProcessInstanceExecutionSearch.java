package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流程执行id数据查询类
 *
 * @author 121232224@qq.com
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenProcessInstanceExecutionSearch对象", description = "流程执行id数据Search对象")
public class OpenProcessInstanceExecutionSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "流程实例id")
    private String processInstanceId;

    @Schema(description = "节点id")
    private String nodeId;

    @Schema(description = "执行id")
    private String executionId;

    @Schema(description = "上级执行id")
    private String parentExecutionId;

    @Schema(description = "流程id")
    private String flowId;
}
