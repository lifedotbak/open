package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenRoleMenu;
import com.spyker.commons.service.OpenRoleMenuService;

import com.spyker.commons.search.OpenRoleMenuSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 角色和菜单关联表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenRoleMenuServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenRoleMenuService service;

    @Test
    public void get() {
        OpenRoleMenu result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenRoleMenu add = new OpenRoleMenu();

        add.setRoleId("roleId");

        add.setMenuId("menuId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenRoleMenu update = new OpenRoleMenu();

        update.setId("id");

        update.setRoleId("roleId");

        update.setMenuId("menuId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenRoleMenuSearch search = new OpenRoleMenuSearch();

        search.setRoleId("roleId");

        search.setMenuId("menuId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenRoleMenu> page = new Page<>(1, 10);

        OpenRoleMenuSearch search = new OpenRoleMenuSearch();

        search.setRoleId("roleId");

        search.setMenuId("menuId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}