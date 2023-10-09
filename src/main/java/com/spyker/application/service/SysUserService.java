package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.SysUser;
import com.spyker.application.search.SysUserSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysUserService extends IService<SysUser> {

    List<SysUser> query(SysUserSearch search);

    IPage<SysUser> queryPage(IPage<SysUser> page, SysUserSearch search);

    SysUser get(String id);

    RestResponse<?> insert(SysUser SysUser);

    RestResponse<?> update(SysUser SysUser);

    RestResponse<?> delete(String id);

}