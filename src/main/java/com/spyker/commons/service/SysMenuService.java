package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysMenu;
import com.spyker.commons.search.SysMenuSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/**
 * 菜单权限表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> query(SysMenuSearch search);

    IPage<SysMenu> queryPage(IPage<SysMenu> page, SysMenuSearch search);

    SysMenu get(String id);

    RestResponse<?> insert(SysMenu sysMenu);

    RestResponse<?> update(SysMenu sysMenu);

    RestResponse<?> delete(String id);
}