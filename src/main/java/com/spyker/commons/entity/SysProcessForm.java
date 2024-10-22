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

/**
 * 流程表单
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@RedisHash
@Data
@Accessors(chain = true)
@TableName("sys_process_form")
@Schema(name = "SysProcessForm", description = "流程表单对象")
public class SysProcessForm {

    @Schema(description = "PK")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "流程唯一id")
    private String uniqueId;

    @Schema(description = "表单名称")
    private String formName;

    @Schema(description = "表单id")
    private String formId;

    @Schema(description = "表单类型")
    private String formType;

    @Schema(description = "表单属性")
    private String props;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "流程id")
    private String flowId;
}