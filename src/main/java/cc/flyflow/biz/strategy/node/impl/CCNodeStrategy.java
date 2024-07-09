package cc.flyflow.biz.strategy.node.impl;

import cc.flyflow.biz.constants.NodeFormatUserVoStatusEnum;
import cn.hutool.core.util.StrUtil;
import cc.flyflow.biz.strategy.node.NodeStrategy;
import cc.flyflow.biz.vo.node.NodeFormatUserVo;
import cc.flyflow.biz.vo.node.NodeShowVo;
import cc.flyflow.common.constants.NodeTypeEnum;
import cc.flyflow.common.dto.flow.Node;
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