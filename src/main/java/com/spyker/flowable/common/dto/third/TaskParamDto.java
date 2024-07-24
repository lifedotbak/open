package com.spyker.flowable.common.dto.third;

import lombok.Data;

/** 任务完成参数对象 */
@Data
public class TaskParamDto {

    /** 实例id */
    private String processInstanceId;

    /** 节点id */
    private String nodeId;

    /** 任务id */
    private String taskId;

    /** 用户id */
    private String userId;

    /** 流程id */
    private String flowId;

    /** 组名字 */
    private String groupName;

    /** 流程名称 */
    private String flowName;
}