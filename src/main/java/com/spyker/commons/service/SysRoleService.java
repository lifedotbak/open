package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysRole;
import com.spyker.commons.search.SysRoleSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> query(SysRoleSearch search);

    IPage<SysRole> queryPage(IPage<SysRole> page, SysRoleSearch search);

    SysRole get(String id);

    RestResponse<?> insert(SysRole SysRole);

    RestResponse<?> update(SysRole SysRole);

    RestResponse<?> delete(String id);

}