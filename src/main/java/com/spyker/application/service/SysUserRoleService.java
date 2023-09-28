package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.SysUserRole;
import com.spyker.application.search.SysUserRoleSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    List<SysUserRole> query(SysUserRoleSearch search);

    IPage<SysUserRole> queryPage(IPage<SysUserRole> page, SysUserRoleSearch search);

    SysUserRole get(String id);

    RestResponse<?> insert(SysUserRole SysUserRole);

    RestResponse<?> update(SysUserRole SysUserRole);

    RestResponse<?> delete(String id);

}