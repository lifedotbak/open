package cc.flyflow.core.node.impl;

import cc.flyflow.common.constants.NodeUserTypeEnum;
import cc.flyflow.common.constants.ProcessInstanceConstant;
import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.flow.Node;
import cc.flyflow.common.dto.flow.NodeUser;
import cc.flyflow.common.dto.flow.node.parent.SuperUserNode;
import cc.flyflow.common.dto.third.DeptDto;
import cc.flyflow.core.node.AssignUserStrategy;
import cc.flyflow.core.utils.BizHttpUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 指定具体用户
 *
 * @author Huijun Zhao
 * @description
 * @date 2023-07-07 13:42
 */
@Component
public class AssignUserFixedStrategyImpl implements InitializingBean, AssignUserStrategy {
    @Override
    public List<String> handle(
            Node node, String rootUserId, Map<String, Object> variables, String tenantId) {
        SuperUserNode superUserNode = (SuperUserNode) node;

        // 指定人员
        List<NodeUser> userDtoList = superUserNode.getNodeUserList();
        // 用户id
        List<String> userIdList =
                userDtoList.stream()
                        .filter(w -> StrUtil.equals(w.getType(), NodeUserTypeEnum.USER.getKey()))
                        .map(w -> Convert.toStr(w.getId()))
                        .collect(Collectors.toList());
        // 部门id
        List<String> deptIdList =
                userDtoList.stream()
                        .filter(w -> StrUtil.equals(w.getType(), NodeUserTypeEnum.DEPT.getKey()))
                        .map(w -> Convert.toStr(w.getId()))
                        .collect(Collectors.toList());

        if (CollUtil.isNotEmpty(deptIdList)) {

            Set<String> deptIdSet = new LinkedHashSet<>(deptIdList);

            List<String> queryChildrenDeptIdList = new ArrayList<>();

            for (NodeUser nodeUser : userDtoList) {
                // 找出来包含子级的
                if (StrUtil.equals(nodeUser.getType(), NodeUserTypeEnum.DEPT.getKey())
                        && nodeUser.getContainChildrenDept()) {
                    queryChildrenDeptIdList.add(nodeUser.getId());
                }
            }

            Map<String, List<DeptDto>> childrenDeptMap =
                    BizHttpUtil.batchQueryChildDeptList(queryChildrenDeptIdList, tenantId)
                            .getData();
            for (Map.Entry<String, List<DeptDto>> entry : childrenDeptMap.entrySet()) {
                Set<String> collect =
                        entry.getValue().stream().map(DeptDto::getId).collect(Collectors.toSet());
                deptIdSet.addAll(collect);
            }

            deptIdList.clear();
            deptIdList.addAll(deptIdSet);

            R<List<String>> r = BizHttpUtil.queryUserIdListByDepIdList(deptIdList, tenantId);

            List<String> data = r.getData();
            if (CollUtil.isNotEmpty(data)) {
                for (String datum : data) {
                    if (!userIdList.contains(datum)) {
                        userIdList.add(datum);
                    }
                }
            }
        }
        return userIdList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(ProcessInstanceConstant.AssignedTypeClass.USER);
    }
}