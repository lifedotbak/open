package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流程主表查询类
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysProcessMainSearch对象", description = "流程主表Search对象")
public class SysProcessMainSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "表单名称")
    private String name;

    @Schema(description = "图标配置")
    private String logo;

    @Schema(description = "分组ID")
    private String groupId;

    @Schema(description = "")
    private Integer processMainSort;

    @Schema(description = "唯一性id")
    private String uniqueId;

    @Schema(description = "范围描述显示")
    private String rangeShow;

    @Schema(description = "租户id")
    private String tenantId;
}
