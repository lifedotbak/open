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
 * 流程记录
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@RedisHash
@Data
@Accessors(chain = true)
@TableName("sys_process_instance_record")
@Schema(name = "SysProcessInstanceRecord", description = "流程记录对象")
public class SysProcessInstanceRecord {

    @Schema(description = "PK")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "流程名字")
    private String name;

    @Schema(description = "头像")
    private String logo;

    @Schema(description = "发起人的用户id")
    private String userId;

    @Schema(description = "发起人主部门id")
    private String mainDeptId;

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

    @Schema(description = "流程实例业务编码")
    private String processInstanceBizCode;

    @Schema(description = "流程实例业务key")
    private String processInstanceBizKey;

    @Schema(description = "表单数据")
    private String formData;

    @Schema(description = "组id")
    private String groupId;

    @Schema(description = "组名称")
    private String groupName;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "结束时间")
    private Date endTime;

    @Schema(description = "上级流程实例id")
    private String parentProcessInstanceId;

    private String process;

    @Schema(description = "结果")
    private Integer result;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "主流程的节点执行id")
    private String parentProcessNodeExecutionId;
}
