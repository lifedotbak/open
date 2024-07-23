package com.flyflow.biz.strategy.assignedtype.impl;

import cn.hutool.extra.spring.SpringUtil;

import com.flyflow.biz.strategy.assignedtype.ApprovalNodeAssignedTypeStrategy;
import com.flyflow.biz.vo.node.NodeFormatUserVo;
import com.flyflow.biz.vo.node.NodeShowVo;
import com.flyflow.common.constants.ProcessInstanceConstant;
import com.flyflow.common.dto.flow.Node;
import com.flyflow.common.dto.flow.NodeUser;
import com.flyflow.common.dto.flow.node.parent.SuperUserNode;
import com.flyflow.common.service.biz.IRemoteService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhj
 */
@Component
@Slf4j
public class RoleStrategyImpl implements ApprovalNodeAssignedTypeStrategy, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(ProcessInstanceConstant.AssignedTypeClass.ROLE);
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

        // 角色
        List<NodeUser> nodeUserList = superUserNode.getNodeUserList();

        List<String> roleIdList =
                nodeUserList.stream().map(w -> w.getId()).collect(Collectors.toList());
        // 去获取主管

        IRemoteService remoteService = SpringUtil.getBean(IRemoteService.class);

        List<String> userIdList = remoteService.queryUserIdListByRoleIdList(roleIdList).getData();

        for (String s : userIdList) {
            NodeFormatUserVo nodeFormatUserVo = buildUser(s);
            nodeFormatUserVoList.add(nodeFormatUserVo);
        }
    }
}