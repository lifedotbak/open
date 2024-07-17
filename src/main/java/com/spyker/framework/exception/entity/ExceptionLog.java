package com.spyker.framework.exception.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionLog {

    @Schema(description = "异常日志ID")
    private String id;

    @Schema(description = "异常的url")
    private String expUrl;

    @Schema(description = "异常的参数")
    private String expParams;

    @Schema(description = "异常的类型")
    private String expType;

    @Schema(description = "异常的类名")
    private String expController;

    @Schema(description = "异常的方法名")
    private String expMethod;

    @Schema(description = "异常详细信息")
    private String expDetail;

    @Schema(description = "创建时间")
    private Date createTime;
}