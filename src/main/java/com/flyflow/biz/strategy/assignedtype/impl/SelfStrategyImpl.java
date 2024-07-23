package com.flyflow.biz.strategy.assignedtype.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import com.flyflow.biz.entity.ProcessInstanceRecord;
import com.flyflow.biz.service.IProcessInstanceRecordService;
import com.flyflow.biz.strategy.assignedtype.ApprovalNodeAssignedTypeStrategy;
import com.flyflow.biz.vo.node.NodeFormatUserVo;
import com.flyflow.biz.vo.node.NodeShowVo;
import com.flyflow.common.constants.ProcessInstanceConstant;
import com.flyflow.common.dto.flow.Node;

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