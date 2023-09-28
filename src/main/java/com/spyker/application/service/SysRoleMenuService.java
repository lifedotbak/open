package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.SysRoleMenu;
import com.spyker.application.search.SysRoleMenuSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    List<SysRoleMenu> query(SysRoleMenuSearch search);

    IPage<SysRoleMenu> queryPage(IPage<SysRoleMenu> page, SysRoleMenuSearch search);

    SysRoleMenu get(String id);

    RestResponse<?> insert(SysRoleMenu SysRoleMenu);

    RestResponse<?> update(SysRoleMenu SysRoleMenu);

    RestResponse<?> delete(String id);

}