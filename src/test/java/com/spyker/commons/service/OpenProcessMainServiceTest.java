package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessMainService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessMain;
import com.spyker.commons.service.OpenProcessMainService;

import com.spyker.commons.search.OpenProcessMainSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 流程主表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessMainServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessMainService service;

    @Test
    public void get() {
        OpenProcessMain result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessMain add = new OpenProcessMain();

        add.setName("name");

        add.setLogo("logo");

        add.setSortValue(1);

        add.setUniqueId("uniqueId");

        add.setRangeShow("rangeShow");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessMain update = new OpenProcessMain();

        update.setId("id");

        update.setName("name");

        update.setLogo("logo");

        update.setSortValue(1);

        update.setUniqueId("uniqueId");

        update.setRangeShow("rangeShow");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessMainSearch search = new OpenProcessMainSearch();

        search.setName("name");

        search.setLogo("logo");

        search.setSortValue(1);

        search.setUniqueId("uniqueId");

        search.setRangeShow("rangeShow");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessMain> page = new Page<>(1, 10);

        OpenProcessMainSearch search = new OpenProcessMainSearch();

        search.setName("name");

        search.setLogo("logo");

        search.setSortValue(1);

        search.setUniqueId("uniqueId");

        search.setRangeShow("rangeShow");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}