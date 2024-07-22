package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessGroup;
import com.spyker.commons.service.OpenProcessGroupService;

import com.spyker.commons.search.OpenProcessGroupSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessGroupServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessGroupService service;

    @Test
    public void get() {
        OpenProcessGroup result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessGroup add = new OpenProcessGroup();

        add.setGroupName("groupName");

        add.setSortValue(1);

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessGroup update = new OpenProcessGroup();

        update.setId("id");

        update.setGroupName("groupName");
        update.setSortValue(1);

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessGroupSearch search = new OpenProcessGroupSearch();

        search.setGroupName("groupName");
        search.setSortValue(1);

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessGroup> page = new Page<>(1, 10);

        OpenProcessGroupSearch search = new OpenProcessGroupSearch();

        search.setGroupName("groupName");

        search.setSortValue(1);

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}