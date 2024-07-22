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
 * 定时任务调度表
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@TableName("sys_job")
@Schema(name = "SysJob", description = "定时任务调度表对象")
public class SysJob {

    @Schema(description = "任务ID")
    @TableId(value = "job_id", type = IdType.ASSIGN_UUID)
    private String jobId;

    @Schema(description = "任务名称")
    @TableId(value = "job_name", type = IdType.ASSIGN_UUID)
    private String jobName;

    @Schema(description = "任务组名")
    @TableId(value = "job_group", type = IdType.ASSIGN_UUID)
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
    @TableField(fill = FieldFill.INSERT)
    // @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    // @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    // @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    // @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "备注信息")
    private String remark;
}