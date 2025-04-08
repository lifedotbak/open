package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysUser;
import com.spyker.commons.search.SysUserSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/** 用户信息表 服务类 */
public interface SysUserService extends IService<SysUser> {

    RestResponse<?> delete(String id);

    void deleteUserThrowException(String id);

    SysUser get(String id);

    RestResponse<?> insert(SysUser sysUser);

    boolean login(String userName, String password);

    List<SysUser> query(SysUserSearch search);

    IPage<SysUser> queryPage(IPage<SysUser> page, SysUserSearch search);

    RestResponse<?> update(SysUser sysUser);
}
