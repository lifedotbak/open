package com.flyflow.biz.vo;

import com.flyflow.biz.entity.Process;
import com.flyflow.common.dto.flow.NodeUser;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

/** 流程对象 */
@Schema(description = "流程对象")
@Data
public class ProcessVO extends Process {
    /** 需要发起人选择的节点id */
    @Schema(description = "需要发起人选择的节点id")
    private List<String> selectUserNodeId;

    /** 发起人范围 */
    @Schema(description = "发起人范围")
    private List<NodeUser> rangeList;

    /** 变量参数集合 */
    @Schema(description = "变量参数集合")
    private Map<String, Object> variableMap;

    /** 是否直接发布 */
    @Schema(description = "是否直接发布")
    private Boolean publish = true;
}