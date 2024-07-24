package com.spyker.flowable.biz.strategy.assignedtype.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.spring.SpringUtil;

import com.spyker.flowable.biz.strategy.assignedtype.ApprovalNodeAssignedTypeStrategy;
import com.spyker.flowable.biz.vo.node.NodeFormatUserVo;
import com.spyker.flowable.biz.vo.node.NodeShowVo;
import com.spyker.flowable.common.constants.ProcessInstanceConstant;
import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.dto.flow.Node;
import com.spyker.flowable.common.dto.flow.node.parent.SuperUserNode;
import com.spyker.flowable.common.dto.third.DeptDto;
import com.spyker.flowable.common.service.biz.IRemoteService;

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
public class LeaderStrategyImpl implements ApprovalNodeAssignedTypeStrategy, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(ProcessInstanceConstant.AssignedTypeClass.LEADER);
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

        SuperUserNode superUserNode = (SuperUserNode) node;

        // 制定主管

        // 指定主管审批
        // 第几级主管审批
        Integer level = superUserNode.getDeptLeaderLevel();

        // 去获取主管

        IRemoteService remoteService = SpringUtil.getBean(IRemoteService.class);

        R<List<DeptDto>> r =
                remoteService.queryParentDeptList(
                        MapUtil.getStr(
                                paramMap,
                                ProcessInstanceConstant.VariableKey.START_USER_MAIN_DEPTID_KEY));

        List<DeptDto> deptDtoList = r.getData();
        if (CollUtil.isNotEmpty(deptDtoList)) {
            if (deptDtoList.size() >= level) {
                DeptDto deptDto = deptDtoList.get(level - 1);

                List<String> leaderUserIdList = deptDto.getLeaderUserIdList();
                for (String s : leaderUserIdList) {
                    NodeFormatUserVo nodeFormatUserVo = buildUser(s);
                    nodeFormatUserVoList.add(nodeFormatUserVo);
                }
            }
        }
    }
}