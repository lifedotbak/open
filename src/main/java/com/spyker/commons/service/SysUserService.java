package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysUser;
import com.spyker.commons.search.SysUserSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/**
 * 用户信息表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysUserService extends IService<SysUser> {

    List<SysUser> query(SysUserSearch search);

    IPage<SysUser> queryPage(IPage<SysUser> page, SysUserSearch search);

    SysUser get(String id);

    RestResponse<?> insert(SysUser sysUser);

    RestResponse<?> update(SysUser sysUser);

    RestResponse<?> delete(String id);

    boolean login(String userName, String password);
}