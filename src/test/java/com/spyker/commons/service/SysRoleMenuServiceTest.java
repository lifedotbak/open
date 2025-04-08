package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysRoleMenu;
import com.spyker.commons.search.SysRoleMenuSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 角色和菜单关联表 服务测试类 */
@Slf4j
public class SysRoleMenuServiceTest extends BaseTest {

    @Autowired private SysRoleMenuService service;

    @Test
    public void add() {
        SysRoleMenu add = new SysRoleMenu();

        add.setRoleId("roleId");

        add.setMenuId("menuId");

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void get() {
        SysRoleMenu result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void query() {
        SysRoleMenuSearch search = new SysRoleMenuSearch();

        search.setRoleId("roleId");

        search.setMenuId("menuId");
        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysRoleMenu> page = new Page<>(1, 10);

        SysRoleMenuSearch search = new SysRoleMenuSearch();

        search.setRoleId("roleId");

        search.setMenuId("menuId");

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }

    @Test
    public void update() {
        SysRoleMenu update = new SysRoleMenu();

        update.setId("id");

        update.setRoleId("roleId");

        update.setMenuId("menuId");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }
}
