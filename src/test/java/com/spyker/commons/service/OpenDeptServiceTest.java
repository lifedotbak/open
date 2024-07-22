package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenDeptService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenDept;
import com.spyker.commons.service.OpenDeptService;

import com.spyker.commons.search.OpenDeptSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 部门表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenDeptServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenDeptService service;

    @Test
    public void get() {
        OpenDept result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenDept add = new OpenDept();

        add.setName("name");

        add.setStatus(1);

        add.setSortValue(1);

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenDept update = new OpenDept();

        update.setId("id");

        update.setName("name");

        update.setStatus(1);

        update.setSortValue(1);

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenDeptSearch search = new OpenDeptSearch();

        search.setName("name");

        search.setStatus(1);

        search.setSortValue(1);

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenDept> page = new Page<>(1, 10);

        OpenDeptSearch search = new OpenDeptSearch();

        search.setName("name");

        search.setStatus(1);

        search.setSortValue(1);

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}