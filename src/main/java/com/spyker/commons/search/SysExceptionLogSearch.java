package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 异常日志表查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "SysExceptionLogSearch对象", description = "异常日志表Search对象")
public class SysExceptionLogSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "异常url")
    private String expUrl;

    @Schema(description = "异常参数")
    private String expParams;

    @Schema(description = "异常类型")
    private String expType;

    @Schema(description = "异常controller")
    private String expController;

    @Schema(description = "异常方法")
    private String expMethod;

    @Schema(description = "异常详情")
    private String expDetail;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "备注")
    private String remark;
}
