package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.SysRoleMenu;
import com.spyker.commons.service.SysRoleMenuService;

import com.spyker.commons.search.SysRoleMenuSearch;
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
public class SysRoleMenuServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private SysRoleMenuService service;

    @Test
    public void get() {
        SysRoleMenu result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysRoleMenu add = new SysRoleMenu();

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysRoleMenu update = new SysRoleMenu();

        update.setRoleId("roleId");

        update.setMenuId("menuId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysRoleMenuSearch search = new SysRoleMenuSearch();

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysRoleMenu> page = new Page<>(1, 10);

        SysRoleMenuSearch search = new SysRoleMenuSearch();

        service.queryPage(page, search);
    }
}