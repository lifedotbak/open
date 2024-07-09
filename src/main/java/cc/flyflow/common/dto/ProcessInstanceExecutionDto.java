package cc.flyflow.common.dto;

import lombok.Getter;
import lombok.Setter;

/** 流程实例执行id表 */
@Getter
@Setter
public class ProcessInstanceExecutionDto {
    /** 执行id */
    private String executionId;

    /** 上级的执行id */
    private String parentIdExecutionId;

    /** 实例id */
    private String processInstanceId;

    /** 流程id */
    private String flowId;

    /** 节点id */
    private String nodeId;

    /** 租户id */
    private String tenantId;
}