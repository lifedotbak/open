package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysMenu;
import com.spyker.commons.search.SysMenuSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 菜单权限表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Slf4j
public class SysMenuServiceTest extends BaseTest {

    @Autowired private SysMenuService service;

    @Test
    public void get() {

        SysMenu result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysMenu add = new SysMenu();

        add.setMenuName("menuName");

        add.setParentId("parentId");

        add.setOrderNum(1);

        add.setPath("path");

        add.setComponent("component");

        add.setQuery("query");

        add.setIsFrame(1);

        add.setIsCache(1);

        add.setMenuType("c");

        add.setVisible("visible");

        add.setPerms("perms");

        add.setIcon("icon");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        service.insert(add);
    }

    @Test
    public void update() {
        SysMenu update = new SysMenu();

        update.setMenuId("menuId");

        update.setMenuName("menuName");

        update.setParentId("parentId");
        update.setOrderNum(1);

        update.setPath("path");

        update.setComponent("component");

        update.setQuery("query");
        update.setIsFrame(1);

        update.setIsCache(1);

        update.setMenuType("menuType");

        update.setVisible("visible");

        update.setPerms("perms");

        update.setIcon("icon");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        service.update(update);
    }

    @Test
    public void query() {
        SysMenuSearch search = new SysMenuSearch();

        search.setMenuName("menuName");
        search.setParentId("parentId");
        search.setPath("path");
        search.setComponent("component");
        search.setQuery("query");
        search.setMenuType("menuType");
        search.setVisible("visible");
        search.setPerms("perms");
        search.setIcon("icon");
        search.setCreateBy("createBy");
        search.setUpdateBy("updateBy");
        search.setRemark("remark");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysMenu> page = new Page<>(1, 10);

        SysMenuSearch search = new SysMenuSearch();

        search.setMenuName("menuName");

        search.setParentId("parentId");

        search.setPath("path");

        search.setComponent("component");

        search.setQuery("query");

        search.setIsFrame(1);

        search.setIsCache(1);

        search.setMenuType("menuType");

        search.setVisible("visible");

        search.setPerms("perms");

        search.setIcon("icon");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.queryPage(page, search);
    }
}