package com.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户-角色
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserRole extends BaseEntity {

    /** 用户id */
    @TableField("`user_id`")
    private String userId;

    /** 角色id */
    @TableField("`role_id`")
    private String roleId;
}