package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcess;
import com.spyker.commons.service.OpenProcessService;

import com.spyker.commons.search.OpenProcessSearch;
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
public class OpenProcessServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessService service;

    @Test
    public void get() {
        OpenProcess result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcess add = new OpenProcess();

        add.setFlowId("flowId");

        add.setName("name");

        add.setLogo("logo");

        add.setSettings("settings");

        add.setFormItems("formItems");

        add.setProcess("process");

        add.setRemark("remark");

        add.setSortValue(1);

        add.setAdminId("adminId");

        add.setUniqueId("uniqueId");

        add.setAdmin("admin");

        add.setRangeShow("rangeShow");

        add.setVersion(1);

        add.setTenantId("tenantId");

        add.setFormItemsPc("formItemsPc");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcess update = new OpenProcess();

        update.setId("id");

        update.setFlowId("flowId");

        update.setName("name");

        update.setLogo("logo");

        update.setSettings("settings");

        update.setFormItems("formItems");

        update.setProcess("process");

        update.setRemark("remark");
        update.setSortValue(1);

        update.setAdminId("adminId");

        update.setUniqueId("uniqueId");

        update.setAdmin("admin");

        update.setRangeShow("rangeShow");
        update.setVersion(1);

        update.setTenantId("tenantId");

        update.setFormItemsPc("formItemsPc");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessSearch search = new OpenProcessSearch();

        search.setFlowId("flowId");

        search.setName("name");

        search.setLogo("logo");

        search.setSettings("settings");

        search.setFormItems("formItems");

        search.setProcess("process");

        search.setRemark("remark");
        search.setSortValue(1);

        search.setAdminId("adminId");

        search.setUniqueId("uniqueId");

        search.setAdmin("admin");

        search.setRangeShow("rangeShow");
        search.setVersion(1);

        search.setTenantId("tenantId");

        search.setFormItemsPc("formItemsPc");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcess> page = new Page<>(1, 10);

        OpenProcessSearch search = new OpenProcessSearch();

        search.setFlowId("flowId");

        search.setName("name");

        search.setLogo("logo");

        search.setSettings("settings");

        search.setFormItems("formItems");

        search.setProcess("process");

        search.setRemark("remark");

        search.setSortValue(1);

        search.setAdminId("adminId");

        search.setUniqueId("uniqueId");

        search.setAdmin("admin");

        search.setRangeShow("rangeShow");

        search.setVersion(1);

        search.setTenantId("tenantId");

        search.setFormItemsPc("formItemsPc");

        service.queryPage(page, search);
    }
}