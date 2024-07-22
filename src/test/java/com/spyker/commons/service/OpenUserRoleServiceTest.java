package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenUserRole;
import com.spyker.commons.service.OpenUserRoleService;

import com.spyker.commons.search.OpenUserRoleSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 用户-角色 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenUserRoleServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenUserRoleService service;

    @Test
    public void get() {
        OpenUserRole result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenUserRole add = new OpenUserRole();

        add.setUserId("userId");

        add.setRoleId("roleId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenUserRole update = new OpenUserRole();

        update.setId("id");

        update.setUserId("userId");

        update.setRoleId("roleId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenUserRoleSearch search = new OpenUserRoleSearch();

        search.setUserId("userId");

        search.setRoleId("roleId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenUserRole> page = new Page<>(1, 10);

        OpenUserRoleSearch search = new OpenUserRoleSearch();

        search.setUserId("userId");

        search.setRoleId("roleId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}