package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysMenu;
import com.spyker.commons.search.SysMenuSearch;

import java.util.List;

/** 菜单管理 服务接口 */
public interface SysMenuService extends IService<SysMenu> {

    boolean delete(String id);

    SysMenu get(String id);

    SysMenu insert(SysMenu sysMenu);

    List<SysMenu> query(SysMenuSearch search);

    IPage<SysMenu> queryPage(IPage<SysMenu> page, SysMenuSearch search);

    SysMenu update(SysMenu sysMenu);
}
