package com.spyker.commons.service;

import com.spyker.commons.entity.OpenRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.OpenRoleMenuSearch;

/**
 * 角色和菜单关联表 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenRoleMenuService extends IService<OpenRoleMenu> {

    // @formatter:off

    List<OpenRoleMenu> query(OpenRoleMenuSearch search);

    IPage<OpenRoleMenu> queryPage(IPage<OpenRoleMenu> page, OpenRoleMenuSearch search);

    OpenRoleMenu get(String id);

    OpenRoleMenu insert(OpenRoleMenu openRoleMenu);

    OpenRoleMenu update(OpenRoleMenu openRoleMenu);

    boolean delete(String id);
}