package com.spyker.flowable.biz.strategy.node.impl;

import cn.hutool.core.util.StrUtil;

import com.spyker.flowable.biz.constants.NodeFormatUserVoStatusEnum;
import com.spyker.flowable.biz.strategy.node.NodeStrategy;
import com.spyker.flowable.biz.vo.node.NodeFormatUserVo;
import com.spyker.flowable.biz.vo.node.NodeShowVo;
import com.spyker.flowable.common.constants.NodeTypeEnum;
import com.spyker.flowable.common.dto.flow.Node;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CCNodeStrategy implements NodeStrategy, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(NodeTypeEnum.CC.getValue());
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
                NodeTypeEnum.CC.getValue(),
                nodeFormatUserVoList);
        if (StrUtil.isAllNotBlank(processInstanceId, node.getExecutionId())) {
            for (NodeFormatUserVo nodeFormatUserVo : nodeFormatUserVoList) {
                nodeFormatUserVo.setStatus(NodeFormatUserVoStatusEnum.YJS.getCode());
            }
        }
    }
}