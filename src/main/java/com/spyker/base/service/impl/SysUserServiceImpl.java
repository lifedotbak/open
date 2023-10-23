package com.spyker.base.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.base.entity.SysUser;
import com.spyker.base.mapper.SysUserMapper;
import com.spyker.base.search.SysUserSearch;
import com.spyker.base.service.SysUserService;
import com.spyker.framework.response.RestResponse;
import com.spyker.framework.util.BCryptUtils;
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

    @Override
    public boolean login(String userName, String password) {

        SysUser sysUser = sysUserMapper.getUserByName(userName);

        if (null != sysUser) {

            if (BCryptUtils.checkpw(password, sysUser.getPassword())) {

                StpUtil.login(sysUser.getUserId());
                return true;
            }

        }

        return false;
    }

}