package com.spyker.framework.exception.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ExceptionLog {

    private static final long serialVersionUID = 1L;

    @Schema(description = "异常日志ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
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