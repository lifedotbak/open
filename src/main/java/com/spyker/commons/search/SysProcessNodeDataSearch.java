package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流程节点数据查询类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysProcessNodeDataSearch对象", description = "流程节点数据Search对象")
public class SysProcessNodeDataSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "表单数据")
    private String data;

    @Schema(description = "")
    private String nodeId;

    @Schema(description = "租户id")
    private String tenantId;
}