package com.spyker.application.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 定时任务调度表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_job")
@Schema(name = "SysJob", description = "$!{table.comment}")
public class SysJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "任务ID")
    @TableId(value = "job_id", type = IdType.ASSIGN_ID)
    private String jobId;

    @Schema(description = "任务名称")
    @TableId(value = "job_name", type = IdType.ASSIGN_ID)
    private String jobName;

    @Schema(description = "任务组名")
    @TableId(value = "job_group", type = IdType.ASSIGN_ID)
    private String jobGroup;

    @Schema(description = "调用目标字符串")
    private String invokeTarget;

    @Schema(description = "cron执行表达式")
    private String cronExpression;

    @Schema(description = "计划执行错误策略（1立即执行 2执行一次 3放弃执行）")
    private String misfirePolicy;

    @Schema(description = "是否并发执行（0允许 1禁止）")
    private String concurrent;

    @Schema(description = "状态（0正常 1暂停）")
    private String status;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新时间")
    private Timestamp updateTime;

    @Schema(description = "备注信息")
    private String remark;
}