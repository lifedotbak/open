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
 * 流程发起人范围
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@RedisHash
@Data
@Accessors(chain = true)
@TableName("sys_process_starter")
@Schema(name = "SysProcessStarter", description = "流程发起人范围对象")
public class SysProcessStarter {

    @Schema(description = "PK")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

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

    @Schema(description = "用户id或者部门id")
    private String typeId;

    @Schema(description = "是否包含下级部门")
    private Integer containChildrenDept;

    @Schema(description = " 类型 user dept")
    private String typeName;

    @Schema(description = "流程表id")
    private String processId;

    @Schema(description = "数据")
    private String data;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "流程id")
    private String flowId;
}