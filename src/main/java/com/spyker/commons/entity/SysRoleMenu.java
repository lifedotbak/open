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

/** 角色和菜单关联表 */
@RedisHash
@Data
@Accessors(chain = true)
@TableName("sys_role_menu")
@Schema(name = "SysRoleMenu", description = "角色和菜单关联表对象")
public class SysRoleMenu {

    @Schema(description = "PK")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "角色ID")
    private String roleId;

    @Schema(description = "菜单ID")
    private String menuId;

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

    @Schema(description = "租户id")
    private String tenantId;
}
