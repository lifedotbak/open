package com.spyker.flowable.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 流程节点记录-执行人
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Getter
@Setter
@Accessors(chain = true)
public class ProcessInstanceAssignUserRecord extends BaseEntity {

    /** 流程id */
    @TableField("`flow_id`")
    private String flowId;

    /** 流程实例id */
    @TableField("`process_instance_id`")
    private String processInstanceId;

    /** 节点执行id */
    private String parentExecutionId;

    /** 表单数据 */
    @TableField("`data`")
    private String data;

    @TableField("`node_id`")
    private String nodeId;

    /** 用户id */
    @TableField("`user_id`")
    private String userId;

    /** 节点状态 */
    @TableField("`status`")
    private Integer status;

    /** 开始时间 */
    @TableField("`start_time`")
    private Date startTime;

    /** 结束时间 */
    @TableField("`end_time`")
    private Date endTime;

    /** 执行id */
    @TableField("`execution_id`")
    private String executionId;

    /** 任务id */
    @TableField("`task_id`")
    private String taskId;

    /** 审批意见 */
    @TableField("`approve_desc`")
    private String approveDesc;

    /** 节点名称 */
    @TableField("`node_name`")
    private String nodeName;

    /** 任务类型 */
    @TableField("`task_type`")
    private String taskType;

    /** 表单本地数据 */
    @TableField("`local_data`")
    private String localData;

    /** 是否是自动完成 */
    @TableField("`auto`")
    private Boolean auto;
}