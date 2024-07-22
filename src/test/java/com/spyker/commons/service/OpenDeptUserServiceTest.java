package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenDeptUserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenDeptUser;
import com.spyker.commons.service.OpenDeptUserService;

import com.spyker.commons.search.OpenDeptUserSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 部门-主管表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenDeptUserServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenDeptUserService service;

    @Test
    public void get() {
        OpenDeptUser result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenDeptUser add = new OpenDeptUser();

        add.setDeptId("deptId");

        add.setUserId("userId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenDeptUser update = new OpenDeptUser();

        update.setId("id");

        update.setDeptId("deptId");

        update.setUserId("userId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenDeptUserSearch search = new OpenDeptUserSearch();

        search.setDeptId("deptId");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenDeptUser> page = new Page<>(1, 10);

        OpenDeptUserSearch search = new OpenDeptUserSearch();

        search.setDeptId("deptId");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}