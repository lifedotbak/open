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

/** 流程抄送数据 */
@RedisHash
@Data
@Accessors(chain = true)
@TableName("sys_process_instance_copy")
@Schema(name = "SysProcessInstanceCopy", description = "流程抄送数据对象")
public class SysProcessInstanceCopy {

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

    @Schema(description = " 流程发起时间")
    private Date startTime;

    @Schema(description = "当前节点时间")
    private Date nodeTime;

    @Schema(description = "发起人")
    private String startUserId;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "实例id")
    private String processInstanceId;

    @Schema(description = "节点id")
    private String nodeId;

    @Schema(description = "分组id")
    private String groupId;

    @Schema(description = "分组名称")
    private String groupName;

    @Schema(description = "流程名称")
    private String processName;

    @Schema(description = "节点 名称")
    private String nodeName;

    @Schema(description = "表单数据")
    private String formData;

    @Schema(description = "抄送人id")
    private String userId;

    @Schema(description = "租户id")
    private String tenantId;
}
