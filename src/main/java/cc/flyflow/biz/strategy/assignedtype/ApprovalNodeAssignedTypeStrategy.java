package cc.flyflow.biz.strategy.assignedtype;

import cc.flyflow.biz.strategy.node.NodeStrategyFactory;
import cc.flyflow.biz.vo.node.NodeFormatUserVo;
import cc.flyflow.biz.vo.node.NodeShowVo;
import cc.flyflow.common.constants.NodeTypeEnum;
import cc.flyflow.common.dto.flow.Node;
import cc.flyflow.common.dto.flow.NodeUser;

import java.util.List;
import java.util.Map;

/** 审批人节点人员类型策略 */
public interface ApprovalNodeAssignedTypeStrategy {

    /**
     * 策略注册方法
     *
     * @param key
     */
    default void afterPropertiesSet(Integer key) {
        ApprovalNodeAssignedTypeStrategyFactory.register(key, this);
    }

    /**
     * 处理评论显示
     *
     * @param node
     * @param processInstanceId
     * @param nodeVo
     * @param nodeFormatUserVoList
     */
    void handle(
            Node node,
            String processInstanceId,
            NodeShowVo nodeVo,
            List<NodeFormatUserVo> nodeFormatUserVoList,
            Map<String, Object> paramMap);

    default NodeFormatUserVo buildUser(String userId) {
        return NodeStrategyFactory.getStrategy(NodeTypeEnum.APPROVAL.getValue()).buildUser(userId);
    }

    default List<NodeFormatUserVo> buildUser(List<NodeUser> nodeUserList) {
        return NodeStrategyFactory.getStrategy(NodeTypeEnum.APPROVAL.getValue())
                .buildUser(nodeUserList);
    }
}