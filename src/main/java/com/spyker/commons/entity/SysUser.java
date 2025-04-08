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

import java.io.Serializable;
import java.util.Date;

/** 用户信息表 */
@RedisHash
@Data
@Accessors(chain = true)
@TableName("sys_user")
@Schema(name = "SysUser", description = "用户信息表对象")
public class SysUser implements Serializable {

    @Schema(description = "pk")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "部门ID")
    private String deptId;

    @Schema(description = "用户账号")
    private String userName;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "拼音  全拼")
    private String pinyin;

    @Schema(description = "拼音, 首字母缩写")
    private String py;

    @Schema(description = "用户类型（00系统用户）")
    private String userType;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "手机号码")
    private String phoneNumber;

    @Schema(description = "用户性别（0男 1女 2未知）")
    private String sex;

    @Schema(description = "头像地址")
    private String avatarUrl;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "帐号状态（0正常 1停用）")
    private String status;

    @Schema(description = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @Schema(description = "最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private Date loginDate;

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

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "直属领导id")
    private String parentId;

    @Schema(description = "租户id")
    private String tenantId;
}
