package com.spyker.flowable.biz.service;

import com.github.yulichang.base.MPJBaseService;
import com.spyker.flowable.biz.entity.DeptUser;
import com.spyker.flowable.common.dto.third.DeptDto;

import java.util.List;

public interface IDeptUserService extends MPJBaseService<DeptUser> {
    /**
     * 查询所有的用户id
     *
     * @param deptId
     * @return
     */
    List<String> queryUserIdList(String deptId);

    /**
     * 查询人员所属的部门jid
     *
     * @param userId
     * @return
     */
    List<String> queryDeptIdList(String userId);

    /**
     * 查询部门集合
     *
     * @param userId
     * @return
     */
    List<DeptDto> queryDeptList(String userId);
}