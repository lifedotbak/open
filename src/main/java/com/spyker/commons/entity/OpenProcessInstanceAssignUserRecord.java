package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 流程节点记录-执行人
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@TableName("open_process_instance_assign_user_record")
@Schema(name = "OpenProcessInstanceAssignUserRecord", description = "流程节点记录-执行人对象")
public class OpenProcessInstanceAssignUserRecord {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    // @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    // @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "流程实例id")
    private String processInstanceId;

    @Schema(description = "表单数据")
    private String data;

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
    private Boolean auto;

    @Schema(description = "节点执行id")
    private String parentExecutionId;
}