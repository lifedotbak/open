package com.flyflow.biz.strategy.assignedtype.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;

import com.flyflow.biz.strategy.assignedtype.ApprovalNodeAssignedTypeStrategy;
import com.flyflow.biz.vo.node.NodeFormatUserVo;
import com.flyflow.biz.vo.node.NodeShowVo;
import com.flyflow.common.constants.ProcessInstanceConstant;
import com.flyflow.common.dto.R;
import com.flyflow.common.dto.flow.Node;
import com.flyflow.common.dto.flow.node.parent.SuperUserNode;
import com.flyflow.common.dto.third.DeptDto;
import com.flyflow.common.service.biz.IRemoteService;

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
public class LeaderTopStrategyImpl implements ApprovalNodeAssignedTypeStrategy, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(ProcessInstanceConstant.AssignedTypeClass.LEADER_TOP);
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
            int index = 1;
            for (DeptDto deptDto : deptDtoList) {
                if (level != null && level < index) {
                    break;
                }

                List<String> leaderUserIdList = deptDto.getLeaderUserIdList();
                for (String s : leaderUserIdList) {
                    boolean b =
                            nodeFormatUserVoList.stream()
                                    .anyMatch(w -> StrUtil.equals(w.getId(), s));
                    if (b) {
                        continue;
                    }
                    NodeFormatUserVo nodeFormatUserVo = buildUser(s);
                    nodeFormatUserVoList.add(nodeFormatUserVo);
                }

                index++;
            }
        }
    }
}