package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.SysMenu;
import com.spyker.application.search.SysMenuSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> query(SysMenuSearch search);

    IPage<SysMenu> queryPage(IPage<SysMenu> page, SysMenuSearch search);

    SysMenu get(String id);

    RestResponse<?> insert(SysMenu SysMenu);

    RestResponse<?> update(SysMenu SysMenu);

    RestResponse<?> delete(String id);

}