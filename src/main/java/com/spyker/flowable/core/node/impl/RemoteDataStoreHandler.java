package com.spyker.flowable.core.node.impl;

import com.spyker.flowable.common.dto.ProcessNodeDataDto;
import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.dto.flow.Node;
import com.spyker.flowable.core.node.IDataStoreHandler;
import com.spyker.flowable.core.utils.BizHttpUtil;
import com.spyker.framework.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/** 远程存储数据处理器 */
@Component("remoteDateStore")
@Slf4j
@Lazy
public class RemoteDataStoreHandler implements IDataStoreHandler {

    /**
     * 节点数据存储
     *
     * @param flowId 流程id
     * @param nodeId 节点id
     * @param data 数据
     * @param tenantId
     */
    @Override
    public void saveAll(String flowId, String nodeId, Object data, String tenantId) {
        ProcessNodeDataDto processNodeDataDto = new ProcessNodeDataDto();
        processNodeDataDto.setFlowId(flowId);
        processNodeDataDto.setNodeId(nodeId);
        processNodeDataDto.setTenantId(tenantId);
        processNodeDataDto.setData(JsonUtil.toJSONString(data));
        BizHttpUtil.saveNodeOriData(processNodeDataDto);
    }

    /**
     * 节点数据存储
     *
     * @param flowId 流程id
     * @param nodeId 节点id
     * @param data 数据
     */
    @Override
    public void save(String flowId, String nodeId, Node data) {
        log.debug("flowId={} nodeId={} data={}", flowId, nodeId, data);
        saveAll(flowId, nodeId, data, null);
    }

    /**
     * 获取节点数据
     *
     * @param flowId 流程id
     * @param nodeId 节点id
     * @param tenantId
     * @return
     */
    @Override
    public String get(String flowId, String nodeId, String tenantId) {

        R<String> r = BizHttpUtil.queryNodeOriData(flowId, nodeId, tenantId);

        log.debug(
                "flowId={} nodeId={} data={} tenantId={}",
                flowId,
                nodeId,
                JsonUtil.toJSONString(r),
                tenantId);

        String data = r.getData();

        return data;
    }
}