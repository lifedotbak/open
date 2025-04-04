package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysRoleMenu;
import com.spyker.commons.search.SysRoleMenuSearch;

import java.util.List;

/**
 * 角色和菜单关联表 服务接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-22
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    List<SysRoleMenu> query(SysRoleMenuSearch search);

    IPage<SysRoleMenu> queryPage(IPage<SysRoleMenu> page, SysRoleMenuSearch search);

    SysRoleMenu get(String id);

    SysRoleMenu insert(SysRoleMenu sysRoleMenu);

    SysRoleMenu update(SysRoleMenu sysRoleMenu);

    boolean delete(String id);
}
