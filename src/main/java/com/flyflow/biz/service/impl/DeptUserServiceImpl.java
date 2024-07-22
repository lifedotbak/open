package com.flyflow.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;

import com.flyflow.biz.entity.Dept;
import com.flyflow.biz.entity.DeptUser;
import com.flyflow.biz.mapper.DeptUserMapper;
import com.flyflow.biz.service.IDeptUserService;
import com.flyflow.common.dto.third.DeptDto;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** 部门主管 */
@Service
@Slf4j
public class DeptUserServiceImpl extends MPJBaseServiceImpl<DeptUserMapper, DeptUser>
        implements IDeptUserService {
    /**
     * 查询所有的用户id
     *
     * @param deptId
     * @return
     */
    @Override
    public List<String> queryUserIdList(String deptId) {
        return this.lambdaQuery().eq(DeptUser::getDeptId, deptId).list().stream()
                .map(DeptUser::getUserId)
                .collect(Collectors.toList());
    }

    /**
     * 查询人员所属的部门jid
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> queryDeptIdList(String userId) {
        return this.lambdaQuery().eq(DeptUser::getUserId, userId).list().stream()
                .map(DeptUser::getDeptId)
                .collect(Collectors.toList());
    }

    /**
     * 查询部门集合
     *
     * @param userId
     * @return
     */
    @Override
    public List<DeptDto> queryDeptList(String userId) {

        MPJLambdaWrapper<DeptUser> lambdaQueryWrapper =
                new MPJLambdaWrapper<DeptUser>()
                        .selectAll(Dept.class)
                        .leftJoin(Dept.class, Dept::getId, DeptUser::getDeptId)
                        .eq(DeptUser::getUserId, userId);
        List<Dept> deptList = this.selectJoinList(Dept.class, lambdaQueryWrapper);
        List<DeptDto> deptDtoList = BeanUtil.copyToList(deptList, DeptDto.class);

        return deptDtoList;
    }
}