package com.flyflow.biz.strategy.node.impl;

import com.flyflow.biz.strategy.node.NodeStrategy;
import com.flyflow.biz.vo.node.NodeFormatUserVo;
import com.flyflow.biz.vo.node.NodeShowVo;
import com.flyflow.common.constants.NodeTypeEnum;
import com.flyflow.common.dto.flow.Node;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ApprovalNodeStrategy implements NodeStrategy, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(NodeTypeEnum.APPROVAL.getValue());
    }

    @Override
    public void handleNodeShow(
            Map<String, Object> paramMap,
            String nodeId,
            List<NodeFormatUserVo> nodeFormatUserVoList,
            String processInstanceId,
            Node node,
            NodeShowVo nodeVo,
            List<String> selectUserNodeIdList) {
        handleUserNode(
                node,
                nodeVo,
                selectUserNodeIdList,
                paramMap,
                processInstanceId,
                NodeTypeEnum.APPROVAL.getValue(),
                nodeFormatUserVoList);
    }
}