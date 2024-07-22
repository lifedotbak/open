package com.spyker.commons.search;

// @formatter:off

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 流程节点记录查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenProcessInstanceNodeRecordSearch对象", description = "流程节点记录Search对象")
public class OpenProcessInstanceNodeRecordSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "流程实例id")
    private String processInstanceId;

    @Schema(description = "表单数据")
    private String data;

    @Schema(description = "")
    private String nodeId;

    @Schema(description = "节点类型")
    private String nodeType;

    @Schema(description = "节点名字")
    private String nodeName;

    @Schema(description = "节点状态")
    private Integer status;

    @Schema(description = "开始时间")
    private Date startTime;

    @Schema(description = "结束时间")
    private Date endTime;

    @Schema(description = "执行id")
    private String executionId;

    @Schema(description = "上一层级id")
    private String parentNodeId;

    @Schema(description = "流转唯一标识")
    private String flowUniqueId;

    @Schema(description = "租户id")
    private String tenantId;
}