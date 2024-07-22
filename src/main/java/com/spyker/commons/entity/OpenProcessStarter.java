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
 * 流程发起人范围
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@TableName("open_process_starter")
@Schema(name = "OpenProcessStarter", description = "流程发起人范围对象")
public class OpenProcessStarter {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    // @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    // @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "用户id或者部门id")
    private String typeId;

    @Schema(description = "是否包含下级部门")
    private Boolean containChildrenDept;

    @Schema(description = " 类型 user dept")
    private String typeName;

    @Schema(description = "流程表id")
    private Long processId;

    @Schema(description = "数据")
    private String data;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "流程id")
    private String flowId;
}