package com.spyker.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.application.entity.SysUser;
import com.spyker.application.mapper.SysUserMapper;
import com.spyker.application.search.SysUserSearch;
import com.spyker.application.service.SysUserService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> query(SysUserSearch search) {
        List<SysUser> SysUserList = sysUserMapper.query(search);

        return SysUserList;
    }

    @Override
    public IPage<SysUser> queryPage(IPage<SysUser> page, SysUserSearch search) {
        page = sysUserMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysUser get(String id) {
        SysUser SysUser = getById(id);

        return SysUser;
    }

    @Override
    public RestResponse<?> insert(SysUser SysUser) {
        save(SysUser);

        return RestResponse.success(SysUser);
    }

    @Override
    public RestResponse<?> update(SysUser SysUser) {
        updateById(SysUser);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}