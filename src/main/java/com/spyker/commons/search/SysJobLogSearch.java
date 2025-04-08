package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 定时任务调度日志表查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "SysJobLogSearch对象", description = "定时任务调度日志表Search对象")
public class SysJobLogSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "任务日志ID")
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
}
