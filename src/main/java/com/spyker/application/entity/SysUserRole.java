package com.spyker.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_user_role")
@Schema(name = "SysUserRole", description = "$!{table.comment}")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private String userId;

    @Schema(description = "角色ID")
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private String roleId;
}