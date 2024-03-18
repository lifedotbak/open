package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysRole;
import com.spyker.commons.mapper.SysRoleMapper;
import com.spyker.commons.search.SysRoleSearch;
import com.spyker.commons.service.SysRoleService;
import com.spyker.framework.response.RestResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色信息表 服务实现类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> query(SysRoleSearch search) {
        List<SysRole> SysRoleList = sysRoleMapper.query(search);

        return SysRoleList;
    }

    @Override
    public IPage<SysRole> queryPage(IPage<SysRole> page, SysRoleSearch search) {
        page = sysRoleMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysRole get(String id) {
        SysRole SysRole = getById(id);

        return SysRole;
    }

    @Override
    public RestResponse<?> insert(SysRole sysRole) {
        save(sysRole);

        return RestResponse.success(sysRole);
    }

    @Override
    public RestResponse<?> update(SysRole sysRole) {
        updateById(sysRole);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }
}