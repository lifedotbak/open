package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessFormService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessForm;
import com.spyker.commons.service.OpenProcessFormService;

import com.spyker.commons.search.OpenProcessFormSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 流程表单 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessFormServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessFormService service;

    @Test
    public void get() {
        OpenProcessForm result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessForm add = new OpenProcessForm();

        add.setUniqueId("uniqueId");

        add.setFormName("formName");

        add.setFormId("formId");

        add.setFormType("formType");

        add.setProps("props");

        add.setTenantId("tenantId");

        add.setFlowId("flowId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessForm update = new OpenProcessForm();

        update.setId("id");

        update.setUniqueId("uniqueId");

        update.setFormName("formName");

        update.setFormId("formId");

        update.setFormType("formType");

        update.setProps("props");

        update.setTenantId("tenantId");

        update.setFlowId("flowId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessFormSearch search = new OpenProcessFormSearch();

        search.setUniqueId("uniqueId");

        search.setFormName("formName");

        search.setFormId("formId");

        search.setFormType("formType");

        search.setProps("props");

        search.setTenantId("tenantId");

        search.setFlowId("flowId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessForm> page = new Page<>(1, 10);

        OpenProcessFormSearch search = new OpenProcessFormSearch();

        search.setUniqueId("uniqueId");

        search.setFormName("formName");

        search.setFormId("formId");

        search.setFormType("formType");

        search.setProps("props");

        search.setTenantId("tenantId");

        search.setFlowId("flowId");

        service.queryPage(page, search);
    }
}