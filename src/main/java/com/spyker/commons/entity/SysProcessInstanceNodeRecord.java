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

/** 流程节点记录 */
@RedisHash
@Data
@Accessors(chain = true)
@TableName("sys_process_instance_node_record")
@Schema(name = "SysProcessInstanceNodeRecord", description = "流程节点记录对象")
public class SysProcessInstanceNodeRecord {

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

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "流程实例id")
    private String processInstanceId;

    @Schema(description = "表单数据")
    private String data;

    private String nodeId;

    @Schema(description = "节点类型")
    private String nodeType;

    @Schema(description = "节点名字")
    private String nodeName;

    @Schema(description = "节点状态")
    private Integer status;

    @Schema(description = "开始时间")
    private Date startTime;

    @Schema(description = "结束时间")
    private Date endTime;

    @Schema(description = "执行id")
    private String executionId;

    @Schema(description = "上一层级id")
    private String parentNodeId;

    @Schema(description = "流转唯一标识")
    private String flowUniqueId;

    @Schema(description = "租户id")
    private String tenantId;
}
