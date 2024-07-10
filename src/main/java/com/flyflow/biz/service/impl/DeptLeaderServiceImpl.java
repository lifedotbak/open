package com.flyflow.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyflow.biz.entity.DeptLeader;
import com.flyflow.biz.mapper.DeptLeaderMapper;
import com.flyflow.biz.service.IDeptLeaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** 部门主管 */
@Service
@Slf4j
public class DeptLeaderServiceImpl extends ServiceImpl<DeptLeaderMapper, DeptLeader>
        implements IDeptLeaderService {
    /**
     * 查询所有的主管id
     *
     * @param deptId
     * @return
     */
    @Override
    public List<String> queryLeaderIdList(String deptId) {
        return this.lambdaQuery().eq(DeptLeader::getDeptId, deptId).list().stream()
                .map(DeptLeader::getUserId)
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
        return this.lambdaQuery().eq(DeptLeader::getUserId, userId).list().stream()
                .map(DeptLeader::getDeptId)
                .collect(Collectors.toList());
    }
}