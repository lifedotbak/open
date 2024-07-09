package cc.flyflow.biz.utils;

import cc.flyflow.biz.constants.ProcessInstanceNodeRecordStatusEnum;
import cc.flyflow.biz.entity.ProcessInstanceNodeRecord;
import cc.flyflow.biz.service.IProcessInstanceNodeRecordService;
import cc.flyflow.biz.strategy.node.NodeStrategy;
import cc.flyflow.biz.strategy.node.NodeStrategyFactory;
import cc.flyflow.biz.vo.node.NodeFormatUserVo;
import cc.flyflow.biz.vo.node.NodeShowVo;
import cc.flyflow.common.constants.NodeTypeEnum;
import cc.flyflow.common.dto.ProcessInstanceNodeRecordParamDto;
import cc.flyflow.common.dto.flow.Node;
import cc.flyflow.common.dto.flow.node.GatewayNode;
import cc.flyflow.common.utils.DateUtil;
import cc.flyflow.common.utils.NodeUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 节点格式化显示工具 */
@Slf4j
public class NodeFormatUtil {

    /**
     * 格式化流程节点显示
     *
     * @param node
     * @param processInstanceId
     * @param paramMap
     * @param processInstanceNodeRecordParamDtoList
     * @param selectUserNodeIdList
     */
    public static List<NodeShowVo> formatProcessNodeShow(
            Node node,
            String processInstanceId,
            Map<String, Object> paramMap,
            List<ProcessInstanceNodeRecordParamDto> processInstanceNodeRecordParamDtoList,
            List<String> selectUserNodeIdList) {
        List<NodeShowVo> list = new ArrayList();

        if (!NodeUtil.isNode(node)) {
            return list;
        }

        String name = node.getNodeName();
        Integer type = node.getType();

        // 构造对象显示
        NodeShowVo nodeShowVo = new NodeShowVo();
        nodeShowVo.setId(node.getId());
        nodeShowVo.setName(name);
        nodeShowVo.setType(type);
        nodeShowVo.setPlaceholder(node.getPlaceHolder());

        // 默认未开始
        nodeShowVo.setStatus(ProcessInstanceNodeRecordStatusEnum.WKS.getCode());
        if (StrUtil.isBlank(node.getExecutionId()) && node.isRemarkedAtNodeShow()) {
            // 这是针对的撤回的 主要是从分支里撤回的
            nodeShowVo.setStatus(ProcessInstanceNodeRecordStatusEnum.YCX.getCode());
        }

        // 执行id
        String executionId = node.getExecutionId();
        ProcessInstanceNodeRecord processNodeRecord = null;
        if (StrUtil.isAllNotBlank(executionId, processInstanceId)) {
            // 节点执行了

            IProcessInstanceNodeRecordService processNodeRecordService =
                    SpringUtil.getBean(IProcessInstanceNodeRecordService.class);
            List<ProcessInstanceNodeRecord> processNodeRecordList =
                    processNodeRecordService
                            .lambdaQuery()
                            .eq(ProcessInstanceNodeRecord::getProcessInstanceId, processInstanceId)
                            .eq(ProcessInstanceNodeRecord::getExecutionId, executionId)
                            .in(
                                    ProcessInstanceNodeRecord::getNodeId,
                                    cc.flyflow.biz.utils.NodeUtil.getFinalNodeIdList(node.getId()))
                            .orderByDesc(ProcessInstanceNodeRecord::getCreateTime)
                            .list();
            processNodeRecord = processNodeRecordList.get(0);

            nodeShowVo.setStatus(processNodeRecord.getStatus());
        }

        if (StrUtil.isAllNotBlank(processInstanceId, node.getExecutionId())) {

            // 处理时间显示
            if (processNodeRecord != null) {
                nodeShowVo.setShowTimeStr(DateUtil.dateShow(processNodeRecord.getStartTime()));
                if (processNodeRecord.getEndTime() != null) {
                    nodeShowVo.setShowTimeStr(DateUtil.dateShow(processNodeRecord.getEndTime()));
                }
            }
        }

        // 处理用户

        List<NodeFormatUserVo> nodeFormatUserVoList = new ArrayList<>();

        NodeStrategy strategy = NodeStrategyFactory.getStrategy(type);
        if (strategy != null) {
            strategy.handleNodeShow(
                    paramMap,
                    node.getId(),
                    nodeFormatUserVoList,
                    processInstanceId,
                    node,
                    nodeShowVo,
                    selectUserNodeIdList);
        }

        nodeShowVo.setUserVoList(nodeFormatUserVoList);

        List<NodeShowVo> branchShowList = new ArrayList<>();

        if (NodeTypeEnum.getByValue(type).getBranch()) {

            GatewayNode gatewayNode = (GatewayNode) node;
            List<Node> branchList = gatewayNode.getConditionNodes();

            if (CollUtil.isNotEmpty(branchList)) {

                // 条件分支

                // 判断当前分支是否执行了
                boolean executed =
                        processInstanceNodeRecordParamDtoList.stream()
                                        .filter(w -> StrUtil.equals(w.getNodeId(), node.getId()))
                                        .filter(
                                                w ->
                                                        StrUtil.equals(
                                                                w.getExecutionId(),
                                                                node.getExecutionId()))
                                        .count()
                                > 0;

                for (Node branch : branchList) {
                    Node children = branch.getChildNode();
                    if (children == null) {
                        continue;
                    }

                    // 子级的数量
                    long childrenCount =
                            processInstanceNodeRecordParamDtoList.stream()
                                    .filter(w -> StrUtil.equals(w.getNodeId(), children.getId()))
                                    .filter(
                                            w ->
                                                    StrUtil.equals(
                                                            w.getExecutionId(),
                                                            children.getExecutionId()))
                                    .count();

                    if (!executed || (childrenCount > 0)) {

                        List<NodeShowVo> processNodeShowDtos =
                                formatProcessNodeShow(
                                        children,
                                        processInstanceId,
                                        paramMap,
                                        processInstanceNodeRecordParamDtoList,
                                        selectUserNodeIdList);

                        NodeShowVo p = new NodeShowVo();
                        p.setChildren(processNodeShowDtos);
                        p.setId(branch.getId());
                        p.setPlaceholder(branch.getPlaceHolder());
                        branchShowList.add(p);
                    }
                }
            }
        }
        nodeShowVo.setBranch(branchShowList);

        list.add(nodeShowVo);

        List<NodeShowVo> next =
                formatProcessNodeShow(
                        node.getChildNode(),
                        processInstanceId,
                        paramMap,
                        processInstanceNodeRecordParamDtoList,
                        selectUserNodeIdList);
        list.addAll(next);

        return list;
    }
}