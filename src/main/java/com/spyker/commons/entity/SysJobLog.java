package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度日志表
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_job_log")
@Schema(name = "SysJobLog", description = "定时任务调度日志表")
public class SysJobLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "任务日志ID")
    @TableId(value = "job_log_id", type = IdType.ASSIGN_ID)
    private String jobLogId;

    @Schema(description = "任务名称")
    private String jobName;

    @Schema(description = "任务组名")
    private String jobGroup;

    @Schema(description = "调用目标字符串")
    private String invokeTarget;

    @Schema(description = "日志信息")
    private String jobMessage;

    @Schema(description = "执行状态（0正常 1失败）")
    private String status;

    @Schema(description = "异常信息")
    private String exceptionInfo;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}