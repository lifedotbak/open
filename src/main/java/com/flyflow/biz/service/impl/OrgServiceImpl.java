package com.flyflow.biz.service.impl;

import com.flyflow.biz.api.ApiStrategyFactory;
import com.flyflow.biz.entity.*;
import com.flyflow.biz.entity.Process;
import com.flyflow.biz.service.*;
import com.flyflow.biz.utils.CoreHttpUtil;
import com.flyflow.biz.utils.DataUtil;
import com.flyflow.biz.utils.DeptUtil;
import com.flyflow.biz.vo.OrgDataVo;
import com.flyflow.biz.vo.OrgSelectShowVo;
import com.flyflow.biz.vo.UserDetailVO;
import com.flyflow.common.constants.NodeUserTypeEnum;
import com.flyflow.common.dto.PageResultDto;
import com.flyflow.common.dto.R;
import com.flyflow.common.dto.TaskDto;
import com.flyflow.common.dto.TaskQueryParamDto;
import com.flyflow.common.dto.flow.NodeUser;
import com.flyflow.common.dto.third.DeptDto;
import com.flyflow.common.dto.third.RoleDto;
import com.flyflow.common.dto.third.UserDto;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrgServiceImpl implements IOrgService {

    @Resource private IUserService userService;
    @Resource private IDeptUserService deptUserService;

    @Resource private IDeptLeaderService deptLeaderService;
    @Resource private IDeptService deptService;

    @Resource private IUserRoleService userRoleService;

    @Resource private IProcessService processService;

    /**
     * 查询组织架构树
     *
     * @param deptId 部门id
     * @param type 只查询部门架构
     * @param showLeave 是否显示离职员工
     * @return 组织架构树数据
     */
    @Override
    public R<OrgSelectShowVo> getOrgTreeData(String deptId, String type, Boolean showLeave) {

        if (StrUtil.equals(type, NodeUserTypeEnum.ROLE.getKey())) {
            // 角色

            List<RoleDto> roleList = ApiStrategyFactory.getStrategy().loadAllRole();

            List<OrgDataVo> orgs = new LinkedList<>();

            for (RoleDto role : roleList) {
                OrgDataVo orgTreeDataVo = new OrgDataVo();
                orgTreeDataVo.setId(role.getId());
                orgTreeDataVo.setName(role.getName());
                orgTreeDataVo.setStatus(role.getStatus());
                orgTreeDataVo.setType(NodeUserTypeEnum.ROLE.getKey());
                orgTreeDataVo.setSelected(false);
                orgs.add(orgTreeDataVo);
            }
            OrgSelectShowVo orgSelectShowVo =
                    new OrgSelectShowVo(new ArrayList<>(), orgs, orgs, new ArrayList<>());

            return R.success(orgSelectShowVo);
        }
        OrgSelectShowVo orgSelectShowVo =
                new OrgSelectShowVo(
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        List<DeptDto> deptList = ApiStrategyFactory.getStrategy().loadAllDept(deptId);

        // 查询所有部门及员工
        {
            List<OrgDataVo> deptVoList = new ArrayList();
            for (DeptDto dept : deptList) {
                OrgDataVo orgTreeDataVo = new OrgDataVo();
                orgTreeDataVo.setId(dept.getId());
                orgTreeDataVo.setName(dept.getName());
                orgTreeDataVo.setType(NodeUserTypeEnum.DEPT.getKey());
                orgTreeDataVo.setSelected(false);
                orgTreeDataVo.setStatus(dept.getStatus());
                deptVoList.add(orgTreeDataVo);
            }
            orgSelectShowVo.setChildDepartments(deptVoList);
        }
        if (!StrUtil.equals(type, NodeUserTypeEnum.DEPT.getKey())) {

            List<OrgDataVo> userVoList = new ArrayList();

            List<UserDto> userList = ApiStrategyFactory.getStrategy().loadUserByDept((deptId));

            for (UserDto user : userList) {
                OrgDataVo orgTreeDataVo = new OrgDataVo();
                orgTreeDataVo.setId(user.getId());
                orgTreeDataVo.setName(user.getName());
                orgTreeDataVo.setType(NodeUserTypeEnum.USER.getKey());
                orgTreeDataVo.setSelected(false);
                orgTreeDataVo.setStatus(user.getStatus());
                orgTreeDataVo.setAvatar(user.getAvatarUrl());
                userVoList.add(orgTreeDataVo);
            }
            orgSelectShowVo.setEmployees(userVoList);
        }

        if (StrUtil.isNotBlank(deptId)) {
            List<DeptDto> allDept = ApiStrategyFactory.getStrategy().loadAllDept(null);
            List<DeptDto> depts = DataUtil.selectParentByDept(deptId, allDept);
            orgSelectShowVo.setTitleDepartments(CollUtil.reverse(depts));
        }

        return R.success(orgSelectShowVo);
    }

    /**
     * 查询所有的组织架构 并树形显示
     *
     * @return
     */
    @Override
    public R getOrgTreeDataAll(String keywords, Integer status) {

        List<DeptDto> deptDtoList = ApiStrategyFactory.getStrategy().loadAllDept(null);

        if (StrUtil.isNotBlank(keywords) || status != null) {
            List list = new ArrayList();
            for (DeptDto dept : deptDtoList) {
                List<String> leaderIdList = dept.getLeaderUserIdList();

                // 主管id集合
                //                List<String> leaderIdList =
                // deptLeaderService.queryLeaderIdList(dept.getId());

                List<NodeUser> leaderUserList = new ArrayList<>();
                for (String s : leaderIdList) {
                    UserDto user = ApiStrategyFactory.getStrategy().getUser(s);
                    NodeUser nodeUser = new NodeUser();
                    nodeUser.setId(s);
                    nodeUser.setName(user.getName());
                    nodeUser.setType(NodeUserTypeEnum.USER.getKey());
                    nodeUser.setSelected(true);
                    nodeUser.setAvatar(user.getAvatarUrl());
                    leaderUserList.add(nodeUser);
                }

                Dict set =
                        Dict.create()
                                .set("leaderUser", leaderUserList)
                                .set("status", dept.getStatus())
                                .set("id", String.valueOf(dept.getId()))
                                .set("name", dept.getName())
                                .set("sort", dept.getSort())
                                .set(
                                        "rootIdList",
                                        CollUtil.reverse(
                                                DeptUtil.queryRootIdList(
                                                        dept.getId(), deptDtoList)));
                list.add(set);
            }
            return R.success(list);
        }

        List<TreeNode<String>> nodeList = CollUtil.newArrayList();

        for (DeptDto dept : deptDtoList) {

            TreeNode<String> treeNode =
                    new TreeNode<>(
                            String.valueOf(dept.getId()),
                            String.valueOf(dept.getParentId()),
                            dept.getName(),
                            1);

            List<String> leaderIdList = dept.getLeaderUserIdList();

            List<NodeUser> leaderUserList = new ArrayList<>();
            for (String s : leaderIdList) {
                UserDto user = ApiStrategyFactory.getStrategy().getUser(s);

                NodeUser nodeUser = new NodeUser();
                nodeUser.setId(s);
                nodeUser.setName(user.getName());
                nodeUser.setType(NodeUserTypeEnum.USER.getKey());
                nodeUser.setSelected(true);
                nodeUser.setAvatar(user.getAvatarUrl());
                leaderUserList.add(nodeUser);
            }

            treeNode.setExtra(
                    Dict.create()
                            .set("leaderUser", leaderUserList)
                            .set("status", dept.getStatus())
                            .set("sort", dept.getSort())
                            .set(
                                    "rootIdList",
                                    CollUtil.reverse(
                                            DeptUtil.queryRootIdList(
                                                    String.valueOf(dept.getId()), deptDtoList))));
            nodeList.add(treeNode);
        }
        // 0表示最顶层的id是0
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");

        return R.success(treeList);
    }

    /**
     * 模糊搜索用户
     *
     * @param userName 用户名/拼音/首字母
     * @return 匹配到的用户
     */
    @Override
    public R<List<OrgDataVo>> getOrgTreeUser(String userName) {

        List<UserDto> userList = ApiStrategyFactory.getStrategy().searchUser(userName);

        List<OrgDataVo> orgTreeDataVoList = new ArrayList<>();

        for (UserDto user : userList) {
            OrgDataVo orgTreeDataVo = new OrgDataVo();
            orgTreeDataVo.setId(user.getId());
            orgTreeDataVo.setName(user.getName());
            orgTreeDataVo.setType(NodeUserTypeEnum.USER.getKey());
            orgTreeDataVo.setAvatar(user.getAvatarUrl());

            orgTreeDataVo.setStatus(user.getStatus());
            orgTreeDataVoList.add(orgTreeDataVo);
        }

        return R.success(orgTreeDataVoList);
    }

    /**
     * 删除部门
     *
     * @param dept
     * @return
     */
    @Override
    public R delete(Dept dept) {
        long id = dept.getId();

        List<DeptDto> allDept = ApiStrategyFactory.getStrategy().loadAllDept(null);
        List<DeptDto> deptList = DataUtil.selectChildrenByDept(String.valueOf(id), allDept);

        Set<String> depIdSet = deptList.stream().map(DeptDto::getId).collect(Collectors.toSet());

        Long count = deptUserService.lambdaQuery().in(DeptUser::getDeptId, depIdSet).count();

        if (count > 0) {
            return R.fail("当前部门下有用户，不能删除");
        }

        deptService.removeById(id);
        return R.success();
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public R getUserDetail(String userId) {

        UserDto user = ApiStrategyFactory.getStrategy().getUser(userId);

        UserDetailVO userDetailVO = BeanUtil.copyProperties(user, UserDetailVO.class);

        //  String deptId = userDetailVO.getDeptId();

        List<String> deptIdList = userDetailVO.getDeptIdList();

        // List<DeptDto> deptList = ApiStrategyFactory.getStrategy().getDeptList(deptIdList);
        // userDetailVO.setDeptName(dept.getName());

        if (user.getParentId() != null) {
            UserDto userDto =
                    ApiStrategyFactory.getStrategy().getUser(String.valueOf(user.getParentId()));
            NodeUser nodeUser = new NodeUser();
            nodeUser.setId(userDto.getId());
            nodeUser.setName(userDto.getName());
            nodeUser.setType(NodeUserTypeEnum.USER.getKey());
            nodeUser.setSelected(true);
            nodeUser.setAvatar(userDto.getAvatarUrl());
            userDetailVO.setParentShow(CollUtil.newArrayList(nodeUser));
        }

        {
            userDetailVO.setDeptIdList(deptIdList);
        }

        userDetailVO.setUserFieldDataList(new ArrayList<>());

        List<String> roleIdList = ApiStrategyFactory.getStrategy().loadRoleIdListByUserId(userId);

        userDetailVO.setRoleIds(roleIdList);

        return R.success(userDetailVO);
    }

    /**
     * 用户离职
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public R delete(User user) {

        // 判断是否有待办任务
        {
            TaskQueryParamDto taskQueryParamDto = new TaskQueryParamDto();
            taskQueryParamDto.setPageNum(1);
            taskQueryParamDto.setPageSize(1);
            taskQueryParamDto.setAssign(String.valueOf(user.getId()));

            R<PageResultDto<TaskDto>> r = CoreHttpUtil.queryTodoTask(taskQueryParamDto);

            PageResultDto<TaskDto> pageResultDto = r.getData();

            Long total = pageResultDto.getTotal();
            if (total > 0) {
                return R.fail("当前用户仍有待办任务，不能离职");
            }
        }
        // 判断是否是流程管理员
        {
            List<Process> processList =
                    processService.lambdaQuery().eq(Process::getAdminId, user.getId()).list();
            if (!processList.isEmpty()) {
                return R.fail(
                        StrUtil.format(
                                "当前用户是流程[{}]的管理员，请先修改流程管理员之后才能离职",
                                processList.stream()
                                        .map(w -> w.getName())
                                        .collect(Collectors.joining(","))));
            }
        }
        // 判断是否是部门负责人
        {
            List<String> deptIdList =
                    deptLeaderService.queryDeptIdList(String.valueOf(user.getId()));

            if (!deptIdList.isEmpty()) {
                List<Dept> deptList = deptService.lambdaQuery().in(Dept::getId, deptIdList).list();
                if (!deptList.isEmpty()) {
                    return R.fail(
                            StrUtil.format(
                                    "当前用户是部门[{}]的负责人，请先修改部门负责人之后才能离职",
                                    deptList.stream()
                                            .map(w -> w.getName())
                                            .collect(Collectors.joining(","))));
                }
            }
        }

        userService.removeById(user.getId());

        // 删除用户和部门的关系
        deptUserService.lambdaUpdate().eq(DeptUser::getUserId, user.getId()).remove();
        userRoleService.lambdaUpdate().eq(UserRole::getUserId, user.getId()).remove();

        return R.success();
    }
}