package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 流程节点记录-执行人查询类
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysProcessInstanceAssignUserRecordSearch对象", description = "流程节点记录-执行人Search对象")
public class SysProcessInstanceAssignUserRecordSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "流程实例id")
    private String processInstanceId;

    @Schema(description = "表单数据")
    private String data;

    @Schema(description = "")
    private String nodeId;

    @Schema(description = " 用户id")
    private String userId;

    @Schema(description = "节点状态")
    private Integer status;

    @Schema(description = "开始时间")
    private Date startTime;

    @Schema(description = "结束时间")
    private Date endTime;

    @Schema(description = "执行id")
    private String executionId;

    @Schema(description = " 任务id")
    private String taskId;

    @Schema(description = "审批意见")
    private String approveDesc;

    @Schema(description = " 节点名称")
    private String nodeName;

    @Schema(description = "任务类型")
    private String taskType;

    @Schema(description = "表单本地数据")
    private String localData;

    @Schema(description = "流转唯一标识")
    private String flowUniqueId;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "是否是自动完成")
    private Integer auto;

    @Schema(description = "节点执行id")
    private String parentExecutionId;
}
