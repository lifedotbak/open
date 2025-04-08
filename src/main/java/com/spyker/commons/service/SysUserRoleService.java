package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysUserRole;
import com.spyker.commons.search.SysUserRoleSearch;

import java.util.List;

/** 用户-角色 服务接口 */
public interface SysUserRoleService extends IService<SysUserRole> {

    boolean delete(String id);

    SysUserRole get(String id);

    SysUserRole insert(SysUserRole sysUserRole);

    List<SysUserRole> query(SysUserRoleSearch search);

    IPage<SysUserRole> queryPage(IPage<SysUserRole> page, SysUserRoleSearch search);

    SysUserRole update(SysUserRole sysUserRole);
}
