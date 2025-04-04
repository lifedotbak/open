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
 * 菜单管理 服务测试类
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Slf4j
public class SysMenuServiceTest extends BaseTest {

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

        add.setParentId("parentId");

        add.setTreePath("treePath");

        add.setName("name");

        add.setMenuType(1);

        add.setPath("path");

        add.setComponent("component");

        add.setPerm("perm");

        add.setVisible(1);

        add.setMenuSort(1);

        add.setIcon("icon");

        add.setRedirect("redirect");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setDelFlag(1);

        add.setTenantId("tenantId");

        add.setAncestors("ancestors");

        add.setQueryParams("queryParams");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysMenu update = new SysMenu();

        update.setId("id");

        update.setParentId("parentId");

        update.setTreePath("treePath");

        update.setName("name");
        update.setMenuType(1);

        update.setPath("path");

        update.setComponent("component");

        update.setPerm("perm");
        update.setVisible(1);

        update.setMenuSort(1);

        update.setIcon("icon");

        update.setRedirect("redirect");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");
        update.setDelFlag(1);

        update.setTenantId("tenantId");

        update.setAncestors("ancestors");

        update.setQueryParams("queryParams");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysMenuSearch search = new SysMenuSearch();

        search.setParentId("parentId");

        search.setTreePath("treePath");

        search.setName("name");
        search.setMenuType(1);

        search.setPath("path");

        search.setComponent("component");

        search.setPerm("perm");
        search.setVisible(1);

        search.setMenuSort(1);

        search.setIcon("icon");

        search.setRedirect("redirect");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");
        search.setDelFlag(1);

        search.setTenantId("tenantId");

        search.setAncestors("ancestors");

        search.setQueryParams("queryParams");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysMenu> page = new Page<>(1, 10);

        SysMenuSearch search = new SysMenuSearch();

        search.setParentId("parentId");

        search.setTreePath("treePath");

        search.setName("name");

        search.setMenuType(1);

        search.setPath("path");

        search.setComponent("component");

        search.setPerm("perm");

        search.setVisible(1);

        search.setMenuSort(1);

        search.setIcon("icon");

        search.setRedirect("redirect");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setDelFlag(1);

        search.setTenantId("tenantId");

        search.setAncestors("ancestors");

        search.setQueryParams("queryParams");

        service.queryPage(page, search);
    }
}
