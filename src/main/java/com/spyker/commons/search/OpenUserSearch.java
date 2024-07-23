package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户表查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenUserSearch对象", description = "用户表Search对象")
public class OpenUserSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "拼音  全拼")
    private String pinyin;

    @Schema(description = "拼音, 首字母缩写")
    private String py;

    @Schema(description = "头像url")
    private String avatarUrl;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "登录密码")
    private String password;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "")
    private Integer status;

    @Schema(description = "直属领导id")
    private Long parentId;

    @Schema(description = "租户id")
    private String tenantId;
}