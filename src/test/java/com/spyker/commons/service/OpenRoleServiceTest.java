package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenRole;
import com.spyker.commons.service.OpenRoleService;

import com.spyker.commons.search.OpenRoleSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 角色 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenRoleServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenRoleService service;

    @Test
    public void get() {
        OpenRole result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenRole add = new OpenRole();

        add.setName("name");

        add.setUserId("userId");

        add.setRoleKey("roleKey");

        add.setStatus(1);

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenRole update = new OpenRole();

        update.setId("id");

        update.setName("name");

        update.setUserId("userId");

        update.setRoleKey("roleKey");
        update.setStatus(1);

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenRoleSearch search = new OpenRoleSearch();

        search.setName("name");

        search.setUserId("userId");

        search.setRoleKey("roleKey");
        search.setStatus(1);

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenRole> page = new Page<>(1, 10);

        OpenRoleSearch search = new OpenRoleSearch();

        search.setName("name");

        search.setUserId("userId");

        search.setRoleKey("roleKey");

        search.setStatus(1);

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}