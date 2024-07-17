package com.spyker.commons.search;

// @formatter:off

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 操作日志记录查询类
 *
 * @author CodeGenerator
 * @since 2024-07-16
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysExceptionLogSearch对象", description = "操作日志记录Search对象")
public class SysExceptionLogSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "异常url")
    private String expUrl;

    @Schema(description = "异常参数")
    private String expParams;

    @Schema(description = "异常类型")
    private String expType;

    @Schema(description = "异常控制类")
    private String expController;

    @Schema(description = "异常方法")
    private String expMethod;

    @Schema(description = "异常详情")
    private String expDetail;

    @Schema(description = "")
    private String updateBy;

    @Schema(description = "")
    private String createBy;
}