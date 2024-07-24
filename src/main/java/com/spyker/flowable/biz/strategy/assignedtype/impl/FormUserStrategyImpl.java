package com.spyker.flowable.biz.strategy.assignedtype.impl;

import cn.hutool.core.util.StrUtil;

import com.spyker.flowable.biz.strategy.assignedtype.ApprovalNodeAssignedTypeStrategy;
import com.spyker.flowable.biz.vo.node.NodeFormatUserVo;
import com.spyker.flowable.biz.vo.node.NodeShowVo;
import com.spyker.flowable.common.constants.ProcessInstanceConstant;
import com.spyker.flowable.common.dto.flow.Node;
import com.spyker.flowable.common.dto.flow.NodeUser;
import com.spyker.flowable.common.dto.flow.node.parent.SuperUserNode;
import com.spyker.framework.util.JsonUtil;

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
public class FormUserStrategyImpl implements ApprovalNodeAssignedTypeStrategy, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(ProcessInstanceConstant.AssignedTypeClass.FORM_USER);
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

        // 表单人员
        String formUser = superUserNode.getFormUserId();

        Object o = paramMap.get(formUser);
        if (o != null) {
            String jsonString = JsonUtil.toJSONString(o);
            if (StrUtil.isNotBlank(jsonString)) {
                List<NodeUser> nodeUserDtoList = JsonUtil.parseArray(jsonString, NodeUser.class);
                List<String> userIdList =
                        nodeUserDtoList.stream().map(w -> (w.getId())).collect(Collectors.toList());
                for (String aLong : userIdList) {
                    nodeFormatUserVoList.add(buildUser(aLong));
                }
            }
        }
    }
}