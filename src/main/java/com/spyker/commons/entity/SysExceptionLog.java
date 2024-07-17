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
 * 操作日志记录
 *
 * @author CodeGenerator
 * @since 2024-07-16
 */
@Data
@Accessors(chain = true)
@TableName("sys_exception_log")
@Schema(name = "SysExceptionLog", description = "操作日志记录对象")
public class SysExceptionLog {

    @Schema(description = "日志主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

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

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;
}