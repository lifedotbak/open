package com.flyflow.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flyflow.biz.entity.ProcessNodeData;
import com.flyflow.common.dto.ProcessNodeDataDto;
import com.flyflow.common.dto.R;
import com.flyflow.common.dto.flow.Node;

/**
 * 流程节点数据 服务类
 *
 * @author Vincent
 * @since 2023-05-07
 */
public interface IProcessNodeDataService extends IService<ProcessNodeData> {

    /**
     * 保存流程节点数据
     *
     * @param processNodeDataDto
     * @return
     */
    R saveNodeData(ProcessNodeDataDto processNodeDataDto);

    /***
     * 获取节点数据
     * @param flowId
     * @param nodeId
     * @return
     */
    R<String> getNodeData(String flowId, String nodeId);

    R<Node> getNode(String flowId, String nodeId);
}