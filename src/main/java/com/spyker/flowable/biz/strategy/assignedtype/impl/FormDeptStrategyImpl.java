package com.spyker.flowable.biz.strategy.assignedtype.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;

import com.spyker.flowable.biz.api.ApiStrategyFactory;
import com.spyker.flowable.biz.strategy.assignedtype.ApprovalNodeAssignedTypeStrategy;
import com.spyker.flowable.biz.vo.node.NodeFormatUserVo;
import com.spyker.flowable.biz.vo.node.NodeShowVo;
import com.spyker.flowable.common.constants.ProcessInstanceConstant;
import com.spyker.flowable.common.dto.flow.Node;
import com.spyker.flowable.common.dto.flow.NodeUser;
import com.spyker.flowable.common.dto.flow.node.parent.SuperUserNode;
import com.spyker.flowable.common.dto.third.DeptDto;
import com.spyker.flowable.common.dto.third.UserQueryDto;
import com.spyker.flowable.common.service.biz.IRemoteService;
import com.spyker.framework.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * @author zhj
 */
@Component
@Slf4j
public class FormDeptStrategyImpl implements ApprovalNodeAssignedTypeStrategy, InitializingBean {

    @Resource private IRemoteService remoteService;

    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(ProcessInstanceConstant.AssignedTypeClass.FORM_DEPT);
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

        // 表单部门
        String formUser = superUserNode.getFormUserId();

        Object o = paramMap.get(formUser);
        if (o != null) {
            String jsonString = JsonUtil.toJSONString(o);
            if (StrUtil.isNotBlank(jsonString)) {
                List<NodeUser> nodeUserDtoList = JsonUtil.parseArray(jsonString, NodeUser.class);
                List<String> deptIdList =
                        nodeUserDtoList.stream().map(NodeUser::getId).collect(Collectors.toList());

                Set<String> deptIdSet = new LinkedHashSet<>(deptIdList);

                Boolean containChildrenDept = superUserNode.getContainChildrenDept();
                if (containChildrenDept != null && containChildrenDept) {
                    // 包含子级

                    // 需要查询下级部门的id集合
                    Map<String, List<DeptDto>> childrenDeptMap =
                            remoteService.batchQueryChildDeptList(deptIdList).getData();
                    for (Map.Entry<String, List<DeptDto>> entry : childrenDeptMap.entrySet()) {
                        Set<String> collect =
                                entry.getValue().stream()
                                        .map(DeptDto::getId)
                                        .collect(Collectors.toSet());
                        deptIdSet.addAll(collect);
                    }
                }
                deptIdList.clear();
                deptIdList.addAll(deptIdSet);

                if (CollUtil.isNotEmpty(deptIdList)) {

                    String deptUserType = superUserNode.getDeptUserType();
                    if (ProcessInstanceConstant.AssignedTypeFormDeptUserTypeClass.ALL_USER.equals(
                            deptUserType)) {

                        UserQueryDto userQueryDto =
                                UserQueryDto.builder().deptIdList(deptIdList).build();

                        List<String> userIdList =
                                ApiStrategyFactory.getStrategy()
                                        .queryUserIdListByRoleIdListAndDeptIdList(userQueryDto);

                        for (String aLong : userIdList) {
                            long count =
                                    nodeFormatUserVoList.stream()
                                            .filter(w -> StrUtil.equals(aLong, w.getId()))
                                            .count();
                            if (count > 0) {
                                continue;
                            }
                            nodeFormatUserVoList.add(buildUser(aLong));
                        }
                    } else if (ProcessInstanceConstant.AssignedTypeFormDeptUserTypeClass.LEADER
                            .equals(deptUserType)) {
                        List<DeptDto> deptDtoList =
                                SpringUtil.getBean(IRemoteService.class)
                                        .queryDeptList(deptIdList)
                                        .getData();
                        for (DeptDto deptDto : deptDtoList) {
                            List<String> leaderUserIdList = deptDto.getLeaderUserIdList();
                            for (String s : leaderUserIdList) {
                                long count =
                                        nodeFormatUserVoList.stream()
                                                .filter(w -> StrUtil.equals(s, w.getId()))
                                                .count();
                                if (count > 0) {
                                    continue;
                                }
                                nodeFormatUserVoList.add(buildUser(s));
                            }
                        }
                    } else if (ProcessInstanceConstant.AssignedTypeFormDeptUserTypeClass.ROLE
                            .equals(deptUserType)) {

                        List<NodeUser> roleList = superUserNode.getRoleList();
                        List<String> roleIdList =
                                roleList.stream()
                                        .map(w -> (w.getId()))
                                        .collect(Collectors.toList());

                        // 部门下角色

                        List<String> userIdList =
                                ApiStrategyFactory.getStrategy()
                                        .queryUserIdListByRoleIdListAndDeptIdList(
                                                UserQueryDto.builder()
                                                        .deptIdList(deptIdList)
                                                        .roleIdList(roleIdList)
                                                        .build());

                        for (String s : userIdList) {
                            nodeFormatUserVoList.add(buildUser(s));
                        }
                    }
                }
            }
        }
    }
}