package com.spyker.flowable.core.node.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;

import com.spyker.flowable.common.constants.ProcessInstanceConstant;
import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.dto.flow.Node;
import com.spyker.flowable.common.dto.flow.node.parent.SuperUserNode;
import com.spyker.flowable.common.dto.third.DeptDto;
import com.spyker.flowable.core.node.AssignUserStrategy;
import com.spyker.flowable.core.utils.BizHttpUtil;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 指定主管
 *
 * @author Huijun Zhao
 * @description
 * @date 2023-07-07 13:42
 */
@Component
public class AssignUserLeaderStrategyImpl implements InitializingBean, AssignUserStrategy {
    @Override
    public List<String> handle(
            Node node, String rootUserId, Map<String, Object> variables, String tenantId) {

        SuperUserNode superUserNode = (SuperUserNode) node;

        List<String> userIdList = new ArrayList<>();

        // 指定主管审批
        // 第几级主管审批
        Integer level = superUserNode.getDeptLeaderLevel();

        // 去获取主管

        R<List<DeptDto>> r =
                BizHttpUtil.queryParentDeptList(
                        MapUtil.getStr(
                                variables,
                                ProcessInstanceConstant.VariableKey.START_USER_MAIN_DEPTID_KEY),
                        tenantId);

        List<DeptDto> deptDtoList = r.getData();
        if (CollUtil.isNotEmpty(deptDtoList)) {
            if (deptDtoList.size() >= level) {
                DeptDto deptDto = deptDtoList.get(level - 1);

                List<String> leaderUserIdList = deptDto.getLeaderUserIdList();

                if (CollUtil.isNotEmpty(leaderUserIdList)) {
                    userIdList.addAll(leaderUserIdList);
                }
            }
        }

        return userIdList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(ProcessInstanceConstant.AssignedTypeClass.LEADER);
    }
}