package com.spyker.flowable.core.node.impl;

import cn.hutool.core.util.StrUtil;

import com.spyker.flowable.common.constants.ProcessInstanceConstant;
import com.spyker.flowable.common.dto.flow.Node;
import com.spyker.flowable.common.dto.flow.NodeUser;
import com.spyker.flowable.common.dto.flow.node.parent.SuperUserNode;
import com.spyker.flowable.core.node.AssignUserStrategy;
import com.spyker.framework.util.JsonUtil;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 来自表单用户
 *
 * @author Huijun Zhao
 * @description
 * @date 2023-07-07 13:42
 */
@Component
public class AssignUserFormUserStrategyImpl implements InitializingBean, AssignUserStrategy {
    @Override
    public List<String> handle(
            Node node, String rootUserId, Map<String, Object> variables, String tenantId) {

        SuperUserNode superUserNode = (SuperUserNode) node;

        List<String> assignList = new ArrayList<>();
        // 表单值

        Object variable = variables.get(superUserNode.getFormUserId());
        if (variable == null) {

        } else if (StrUtil.isBlankIfStr(variable)) {

        } else {

            String jsonString = JsonUtil.toJSONString(variable);
            List<NodeUser> nodeUserDtoList = JsonUtil.parseArray(jsonString, NodeUser.class);

            List<String> userIdList =
                    nodeUserDtoList.stream()
                            .map(w -> String.valueOf(w.getId()))
                            .collect(Collectors.toList());

            assignList.addAll(userIdList);
        }
        return assignList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(ProcessInstanceConstant.AssignedTypeClass.FORM_USER);
    }
}