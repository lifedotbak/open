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
 * 通知消息
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@TableName("sys_message")
@Schema(name = "SysMessage", description = "通知消息对象")
public class SysMessage {

    @Schema(description = "PK")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "类型")
    private String messageType;

    @Schema(description = "是否已读")
    private Integer readed;

    @Schema(description = "用户id")
    private String userId;

    @Schema(description = "业务唯一id")
    private String bizUniqueId;

    @Schema(description = "消息参数")
    private String param;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "消息头")
    private String title;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "流程实例id")
    private String processInstanceId;

    @Schema(description = "租户id")
    private String tenantId;
}