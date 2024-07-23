package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysMenu;
import com.spyker.commons.search.SysMenuSearch;

import java.util.List;

/**
 * 菜单管理 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> query(SysMenuSearch search);

    IPage<SysMenu> queryPage(IPage<SysMenu> page, SysMenuSearch search);

    SysMenu get(String id);

    SysMenu insert(SysMenu sysMenu);

    SysMenu update(SysMenu sysMenu);

    boolean delete(String id);
}