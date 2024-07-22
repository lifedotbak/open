package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenMenu;
import com.spyker.commons.service.OpenMenuService;

import com.spyker.commons.search.OpenMenuSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 菜单管理 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenMenuServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenMenuService service;

    @Test
    public void get() {
        OpenMenu result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenMenu add = new OpenMenu();

        add.setTreePath("treePath");

        add.setName("name");

        add.setPath("path");

        add.setComponent("component");

        add.setPerm("perm");

        add.setSortValue(1);

        add.setIcon("icon");

        add.setRedirect("redirect");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenMenu update = new OpenMenu();

        update.setId("id");

        update.setTreePath("treePath");

        update.setName("name");

        update.setPath("path");

        update.setComponent("component");

        update.setPerm("perm");

        update.setSortValue(1);

        update.setIcon("icon");

        update.setRedirect("redirect");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenMenuSearch search = new OpenMenuSearch();

        search.setTreePath("treePath");

        search.setName("name");

        search.setPath("path");

        search.setComponent("component");

        search.setPerm("perm");

        search.setSortValue(1);

        search.setIcon("icon");

        search.setRedirect("redirect");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenMenu> page = new Page<>(1, 10);

        OpenMenuSearch search = new OpenMenuSearch();

        search.setTreePath("treePath");

        search.setName("name");

        search.setPath("path");

        search.setComponent("component");

        search.setPerm("perm");

        search.setSortValue(1);

        search.setIcon("icon");

        search.setRedirect("redirect");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}