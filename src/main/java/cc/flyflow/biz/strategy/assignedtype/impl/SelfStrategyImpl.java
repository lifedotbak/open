package cc.flyflow.biz.strategy.assignedtype.impl;

import cc.flyflow.biz.entity.ProcessInstanceRecord;
import cc.flyflow.biz.service.IProcessInstanceRecordService;
import cc.flyflow.biz.strategy.assignedtype.ApprovalNodeAssignedTypeStrategy;
import cc.flyflow.biz.vo.node.NodeFormatUserVo;
import cc.flyflow.biz.vo.node.NodeShowVo;
import cc.flyflow.common.constants.ProcessInstanceConstant;
import cc.flyflow.common.dto.flow.Node;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zhj
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SelfStrategyImpl implements ApprovalNodeAssignedTypeStrategy, InitializingBean {

    private final IProcessInstanceRecordService processInstanceRecordService;

    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(ProcessInstanceConstant.AssignedTypeClass.SELF);
    }

    /**
     * 处理评论显示
     *
     * @param node
     * @param processInstanceId
     * @param nodeVo
     * @param nodeFormatUserVoList
     * @param paramMap
     */
    @Override
    public void handle(
            Node node,
            String processInstanceId,
            NodeShowVo nodeVo,
            List<NodeFormatUserVo> nodeFormatUserVoList,
            Map<String, Object> paramMap) {
        // 发起人自己
        if (StrUtil.isNotBlank(processInstanceId)) {
            ProcessInstanceRecord processInstanceRecord =
                    processInstanceRecordService.getByProcessInstanceId(processInstanceId);
            nodeFormatUserVoList.addAll(
                    CollUtil.newArrayList(buildUser(processInstanceRecord.getUserId())));
        } else {
            nodeFormatUserVoList.addAll(
                    CollUtil.newArrayList(buildUser(StpUtil.getLoginIdAsString())));
        }
    }
}