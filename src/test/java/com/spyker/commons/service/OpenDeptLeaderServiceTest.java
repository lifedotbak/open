package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenDeptLeaderService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenDeptLeader;
import com.spyker.commons.service.OpenDeptLeaderService;

import com.spyker.commons.search.OpenDeptLeaderSearch;
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
public class OpenDeptLeaderServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenDeptLeaderService service;

    @Test
    public void get() {
        OpenDeptLeader result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenDeptLeader add = new OpenDeptLeader();

        add.setDeptId("deptId");

        add.setUserId("userId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenDeptLeader update = new OpenDeptLeader();

        update.setId("id");

        update.setDeptId("deptId");

        update.setUserId("userId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenDeptLeaderSearch search = new OpenDeptLeaderSearch();

        search.setDeptId("deptId");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenDeptLeader> page = new Page<>(1, 10);

        OpenDeptLeaderSearch search = new OpenDeptLeaderSearch();

        search.setDeptId("deptId");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}