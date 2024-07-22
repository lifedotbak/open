package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysUserRole;
import com.spyker.commons.search.SysUserRoleSearch;

import java.util.List;

/**
 * 用户和角色关联表 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    // @formatter:off

    List<SysUserRole> query(SysUserRoleSearch search);

    IPage<SysUserRole> queryPage(IPage<SysUserRole> page, SysUserRoleSearch search);

    SysUserRole get(String id);

    SysUserRole insert(SysUserRole sysUserRole);

    SysUserRole update(SysUserRole sysUserRole);

    boolean delete(String id);
}