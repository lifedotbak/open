package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.BaseTest;
import com.spyker.application.entity.SysRoleMenu;
import com.spyker.application.search.SysRoleMenuSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@Slf4j
public class SysRoleMenuServiceTest extends BaseTest {

    @Autowired
    private SysRoleMenuService service;

    @Test
    public void get() {

        SysRoleMenu result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysRoleMenu add = new SysRoleMenu();

        service.insert(add);
    }

    @Test
    public void update() {
        SysRoleMenu update = new SysRoleMenu();

        update.setRoleId("roleId");

        update.setMenuId("menuId");

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