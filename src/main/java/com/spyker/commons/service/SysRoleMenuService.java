package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysRoleMenu;
import com.spyker.commons.search.SysRoleMenuSearch;

import java.util.List;

/** 角色和菜单关联表 服务接口 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    boolean delete(String id);

    SysRoleMenu get(String id);

    SysRoleMenu insert(SysRoleMenu sysRoleMenu);

    List<SysRoleMenu> query(SysRoleMenuSearch search);

    IPage<SysRoleMenu> queryPage(IPage<SysRoleMenu> page, SysRoleMenuSearch search);

    SysRoleMenu update(SysRoleMenu sysRoleMenu);
}
