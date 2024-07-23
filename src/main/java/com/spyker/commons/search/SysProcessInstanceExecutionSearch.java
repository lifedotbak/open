package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流程执行id数据查询类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysProcessInstanceExecutionSearch对象", description = "流程执行id数据Search对象")
public class SysProcessInstanceExecutionSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

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