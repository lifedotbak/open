package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysUserRole;
import com.spyker.commons.search.SysUserRoleSearch;

import java.util.List;

/**
 * 用户-角色 服务接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    List<SysUserRole> query(SysUserRoleSearch search);

    IPage<SysUserRole> queryPage(IPage<SysUserRole> page, SysUserRoleSearch search);

    SysUserRole get(String id);

    SysUserRole insert(SysUserRole sysUserRole);

    SysUserRole update(SysUserRole sysUserRole);

    boolean delete(String id);
}
