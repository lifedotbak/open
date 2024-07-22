package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenMenu;
import com.spyker.commons.search.OpenMenuSearch;

import java.util.List;

/**
 * 菜单管理 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenMenuService extends IService<OpenMenu> {

    // @formatter:off

    List<OpenMenu> query(OpenMenuSearch search);

    IPage<OpenMenu> queryPage(IPage<OpenMenu> page, OpenMenuSearch search);

    OpenMenu get(String id);

    OpenMenu insert(OpenMenu openMenu);

    OpenMenu update(OpenMenu openMenu);

    boolean delete(String id);
}