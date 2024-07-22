package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户和角色关联表
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
@Schema(name = "SysUserRole", description = "用户和角色关联表对象")
public class SysUserRole {

    @Schema(description = "用户ID")
    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String userId;

    @Schema(description = "角色ID")
    @TableId(value = "role_id", type = IdType.ASSIGN_UUID)
    private String roleId;
}