package com.spyker.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户与岗位关联表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_user_post")
@Schema(name = "SysUserPost", description = "$!{table.comment}")
public class SysUserPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private String userId;

    @Schema(description = "岗位ID")
    @TableId(value = "post_id", type = IdType.ASSIGN_ID)
    private String postId;
}