package com.spyker.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.application.entity.SysUserRole;
import com.spyker.application.mapper.SysUserRoleMapper;
import com.spyker.application.search.SysUserRoleSearch;
import com.spyker.application.service.SysUserRoleService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> query(SysUserRoleSearch search) {
        List<SysUserRole> SysUserRoleList = sysUserRoleMapper.query(search);

        return SysUserRoleList;
    }

    @Override
    public IPage<SysUserRole> queryPage(IPage<SysUserRole> page, SysUserRoleSearch search) {
        page = sysUserRoleMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysUserRole get(String id) {
        SysUserRole SysUserRole = getById(id);

        return SysUserRole;
    }

    @Override
    public RestResponse<?> insert(SysUserRole SysUserRole) {
        save(SysUserRole);

        return RestResponse.success(SysUserRole);
    }

    @Override
    public RestResponse<?> update(SysUserRole SysUserRole) {
        updateById(SysUserRole);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}