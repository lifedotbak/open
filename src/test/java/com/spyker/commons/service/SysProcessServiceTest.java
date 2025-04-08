package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcess;
import com.spyker.commons.search.SysProcessSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 服务测试类 */
@Slf4j
public class SysProcessServiceTest extends BaseTest {

    @Autowired private SysProcessService service;

    @Test
    public void add() {
        SysProcess add = new SysProcess();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setFlowId("flowId");

        add.setName("name");

        add.setLogo("logo");

        add.setSettings("settings");

        add.setGroupId("groupId");

        add.setFormItems("formItems");

        add.setProcess("process");

        add.setRemark("remark");

        add.setProcessSort(1);

        add.setIsHidden(1);

        add.setIsStop(1);

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
    public void delete() {
        service.delete("1");
    }

    @Test
    public void get() {
        SysProcess result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void query() {
        SysProcessSearch search = new SysProcessSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setFlowId("flowId");

        search.setName("name");

        search.setLogo("logo");

        search.setSettings("settings");

        search.setGroupId("groupId");

        search.setFormItems("formItems");

        search.setProcess("process");

        search.setRemark("remark");
        search.setProcessSort(1);

        search.setIsHidden(1);

        search.setIsStop(1);

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
        IPage<SysProcess> page = new Page<>(1, 10);

        SysProcessSearch search = new SysProcessSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setFlowId("flowId");

        search.setName("name");

        search.setLogo("logo");

        search.setSettings("settings");

        search.setGroupId("groupId");

        search.setFormItems("formItems");

        search.setProcess("process");

        search.setRemark("remark");

        search.setProcessSort(1);

        search.setIsHidden(1);

        search.setIsStop(1);

        search.setAdminId("adminId");

        search.setUniqueId("uniqueId");

        search.setAdmin("admin");

        search.setRangeShow("rangeShow");

        search.setVersion(1);

        search.setTenantId("tenantId");

        search.setFormItemsPc("formItemsPc");

        service.queryPage(page, search);
    }

    @Test
    public void update() {
        SysProcess update = new SysProcess();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setFlowId("flowId");

        update.setName("name");

        update.setLogo("logo");

        update.setSettings("settings");

        update.setGroupId("groupId");

        update.setFormItems("formItems");

        update.setProcess("process");

        update.setRemark("remark");
        update.setProcessSort(1);

        update.setIsHidden(1);

        update.setIsStop(1);

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
}
