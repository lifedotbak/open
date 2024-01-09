package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;

/**
 * 定时任务调度表
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@Schema(name = "SysJobSearch对象", description = "定时任务调度表Search对象")
public class SysJobSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "任务ID")
    private String jobId;

    @Schema(description = "任务名称")
    private String jobName;

    @Schema(description = "任务组名")
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

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "备注信息")
    private String remark;
}