package com.spyker.application.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@Schema(name = "SysUserSearch对象", description = "用户信息表Search对象")
public class SysUserSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "部门ID")
    private String deptId;

    @Schema(description = "用户账号")
    private String userName;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "用户类型（00系统用户）")
    private String userType;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "手机号码")
    private String phonenumber;

    @Schema(description = "用户性别（0男 1女 2未知）")
    private String sex;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "帐号状态（0正常 1停用）")
    private String status;

    @Schema(description = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @Schema(description = "最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private Timestamp loginDate;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新时间")
    private Timestamp updateTime;

    @Schema(description = "备注")
    private String remark;

}