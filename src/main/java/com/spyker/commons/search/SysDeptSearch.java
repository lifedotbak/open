package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 部门表
 *
 * @author CodeGenerator
 * @since 2023-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(name = "SysDeptSearch对象", description = "部门表Search对象")
public class SysDeptSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "部门id")
    private String deptId;

    @Schema(description = "父部门id")
    private String parentId;

    @Schema(description = "祖级列表")
    private String ancestors;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "负责人")
    private String leader;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "部门状态（0正常 1停用）")
    private String status;

    @Schema(description = "删除标志（0代表存在 2代表删除）")
    private String delFlag;
}