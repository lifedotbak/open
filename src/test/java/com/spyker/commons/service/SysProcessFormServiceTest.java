package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessForm;
import com.spyker.commons.search.SysProcessFormSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程表单 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessFormServiceTest extends BaseTest {

    @Autowired private SysProcessFormService service;

    @Test
    public void get() {
        SysProcessForm result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysProcessForm add = new SysProcessForm();

        add.setDelFlag(1);

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
        SysProcessForm update = new SysProcessForm();

        update.setId("id");
        update.setDelFlag(1);

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
        SysProcessFormSearch search = new SysProcessFormSearch();

        search.setDelFlag(1);

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
        IPage<SysProcessForm> page = new Page<>(1, 10);

        SysProcessFormSearch search = new SysProcessFormSearch();

        search.setDelFlag(1);

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