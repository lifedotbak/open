package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.SysMenu;
import com.spyker.commons.service.SysMenuService;

import com.spyker.commons.search.SysMenuSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 菜单权限表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysMenuServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private SysMenuService service;

    @Test
    public void get() {
        SysMenu result = service.getById("1");
        log.info("result------>{}", result);
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

        add.setAncestors("ancestors");

        add.setOrderNum(1);

        add.setPath("path");

        add.setComponent("component");

        add.setQuery("query");

        add.setIsCache(1);

        add.setIsFrame(1);

        add.setMenuType("menuType");

        add.setVisible("visible");

        add.setStatus("status");

        add.setPerms("perms");

        add.setIcon("icon");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysMenu update = new SysMenu();

        update.setMenuId("menuId");

        update.setMenuName("menuName");

        update.setParentId("parentId");

        update.setAncestors("ancestors");
        update.setOrderNum(1);

        update.setPath("path");

        update.setComponent("component");

        update.setQuery("query");
        update.setIsCache(1);

        update.setIsFrame(1);

        update.setMenuType("menuType");

        update.setVisible("visible");

        update.setStatus("status");

        update.setPerms("perms");

        update.setIcon("icon");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysMenuSearch search = new SysMenuSearch();

        search.setMenuName("menuName");

        search.setParentId("parentId");

        search.setAncestors("ancestors");
        search.setOrderNum(1);

        search.setPath("path");

        search.setComponent("component");

        search.setQuery("query");
        search.setIsCache(1);

        search.setIsFrame(1);

        search.setMenuType("menuType");

        search.setVisible("visible");

        search.setStatus("status");

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

        search.setAncestors("ancestors");

        search.setOrderNum(1);

        search.setPath("path");

        search.setComponent("component");

        search.setQuery("query");

        search.setIsCache(1);

        search.setIsFrame(1);

        search.setMenuType("menuType");

        search.setVisible("visible");

        search.setStatus("status");

        search.setPerms("perms");

        search.setIcon("icon");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.queryPage(page, search);
    }
}