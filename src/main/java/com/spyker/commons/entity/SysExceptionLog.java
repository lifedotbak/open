package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

/** 异常日志表 */
@Data
@Accessors(chain = true)
@TableName("sys_exception_log")
@Schema(name = "SysExceptionLog", description = "异常日志表对象")
@RedisHash
public class SysExceptionLog {

    @Schema(description = "参数主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

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
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "备注")
    private String remark;
}
