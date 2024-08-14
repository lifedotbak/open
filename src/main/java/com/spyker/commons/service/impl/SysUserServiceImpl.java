package com.spyker.commons.service.impl;

import cn.dev33.satoken.stp.StpUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysUser;
import com.spyker.commons.mapper.SysUserMapper;
import com.spyker.commons.search.SysUserSearch;
import com.spyker.commons.service.SysUserService;
import com.spyker.framework.constants.CommonsConstants;
import com.spyker.framework.util.sign.BCryptUtils;
import com.spyker.framework.web.response.RestResponse;

import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户信息表 服务实现类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    private final SysUserMapper sysUserMapper;

    private final HttpSession httpSession;

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
    public RestResponse<?> insert(SysUser sysUser) {
        save(sysUser);

        return RestResponse.success(sysUser);
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

                StpUtil.login(sysUser.getId());

                StpUtil.getSession().set(CommonsConstants.LOGIN_USER_ID, sysUser.getId());
                StpUtil.getSession().set(CommonsConstants.LOGIN_USER_INFO, sysUser);

                httpSession.setAttribute(CommonsConstants.LOGIN_USER_ID, sysUser.getId());

                return true;
            }
        }

        return false;
    }
}