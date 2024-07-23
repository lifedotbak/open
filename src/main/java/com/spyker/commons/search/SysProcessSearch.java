package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysProcessSearch对象", description = "Search对象")
public class SysProcessSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "表单ID")
    private String flowId;

    @Schema(description = "表单名称")
    private String name;

    @Schema(description = "图标配置")
    private String logo;

    @Schema(description = "设置项")
    private String settings;

    @Schema(description = "分组ID")
    private String groupId;

    @Schema(description = "表单设置内容")
    private String formItems;

    @Schema(description = "流程设置内容")
    private String process;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "")
    private Integer processSort;

    @Schema(description = "0 正常 1=隐藏")
    private Integer isHidden;

    @Schema(description = "0 正常 1=停用 ")
    private Integer isStop;

    @Schema(description = "流程管理员")
    private String adminId;

    @Schema(description = "唯一性id")
    private String uniqueId;

    @Schema(description = "管理员")
    private String admin;

    @Schema(description = "范围描述显示")
    private String rangeShow;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "表单设置内容pc")
    private String formItemsPc;
}