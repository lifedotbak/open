package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessStarterService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessStarter;
import com.spyker.commons.service.OpenProcessStarterService;

import com.spyker.commons.search.OpenProcessStarterSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 流程发起人范围 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessStarterServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessStarterService service;

    @Test
    public void get() {
        OpenProcessStarter result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessStarter add = new OpenProcessStarter();

        add.setTypeId("typeId");

        add.setTypeName("typeName");

        add.setData("data");

        add.setTenantId("tenantId");

        add.setFlowId("flowId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessStarter update = new OpenProcessStarter();

        update.setId("id");

        update.setTypeId("typeId");

        update.setTypeName("typeName");

        update.setData("data");

        update.setTenantId("tenantId");

        update.setFlowId("flowId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessStarterSearch search = new OpenProcessStarterSearch();

        search.setTypeId("typeId");

        search.setTypeName("typeName");

        search.setData("data");

        search.setTenantId("tenantId");

        search.setFlowId("flowId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessStarter> page = new Page<>(1, 10);

        OpenProcessStarterSearch search = new OpenProcessStarterSearch();

        search.setTypeId("typeId");

        search.setTypeName("typeName");

        search.setData("data");

        search.setTenantId("tenantId");

        search.setFlowId("flowId");

        service.queryPage(page, search);
    }
}