package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessInstanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessInstanceRecord;
import com.spyker.commons.service.OpenProcessInstanceRecordService;

import com.spyker.commons.search.OpenProcessInstanceRecordSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 流程记录 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessInstanceRecordServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessInstanceRecordService service;

    @Test
    public void get() {
        OpenProcessInstanceRecord result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessInstanceRecord add = new OpenProcessInstanceRecord();

        add.setName("name");

        add.setLogo("logo");

        add.setUserId("userId");

        add.setMainDeptId("mainDeptId");

        add.setFlowId("flowId");

        add.setProcessInstanceId("processInstanceId");

        add.setProcessInstanceBizCode("processInstanceBizCode");

        add.setProcessInstanceBizKey("processInstanceBizKey");

        add.setFormData("formData");

        add.setGroupName("groupName");

        add.setStatus(1);

        add.setParentProcessInstanceId("parentProcessInstanceId");

        add.setProcess("process");

        add.setResult(1);

        add.setTenantId("tenantId");

        add.setParentProcessNodeExecutionId("parentProcessNodeExecutionId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessInstanceRecord update = new OpenProcessInstanceRecord();

        update.setId("id");

        update.setName("name");

        update.setLogo("logo");

        update.setUserId("userId");

        update.setMainDeptId("mainDeptId");

        update.setFlowId("flowId");

        update.setProcessInstanceId("processInstanceId");

        update.setProcessInstanceBizCode("processInstanceBizCode");

        update.setProcessInstanceBizKey("processInstanceBizKey");

        update.setFormData("formData");

        update.setGroupName("groupName");
        update.setStatus(1);

        update.setParentProcessInstanceId("parentProcessInstanceId");

        update.setProcess("process");
        update.setResult(1);

        update.setTenantId("tenantId");

        update.setParentProcessNodeExecutionId("parentProcessNodeExecutionId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessInstanceRecordSearch search = new OpenProcessInstanceRecordSearch();

        search.setName("name");

        search.setLogo("logo");

        search.setUserId("userId");

        search.setMainDeptId("mainDeptId");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setProcessInstanceBizCode("processInstanceBizCode");

        search.setProcessInstanceBizKey("processInstanceBizKey");

        search.setFormData("formData");

        search.setGroupName("groupName");
        search.setStatus(1);

        search.setParentProcessInstanceId("parentProcessInstanceId");

        search.setProcess("process");
        search.setResult(1);

        search.setTenantId("tenantId");

        search.setParentProcessNodeExecutionId("parentProcessNodeExecutionId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessInstanceRecord> page = new Page<>(1, 10);

        OpenProcessInstanceRecordSearch search = new OpenProcessInstanceRecordSearch();

        search.setName("name");

        search.setLogo("logo");

        search.setUserId("userId");

        search.setMainDeptId("mainDeptId");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setProcessInstanceBizCode("processInstanceBizCode");

        search.setProcessInstanceBizKey("processInstanceBizKey");

        search.setFormData("formData");

        search.setGroupName("groupName");

        search.setStatus(1);

        search.setParentProcessInstanceId("parentProcessInstanceId");

        search.setProcess("process");

        search.setResult(1);

        search.setTenantId("tenantId");

        search.setParentProcessNodeExecutionId("parentProcessNodeExecutionId");

        service.queryPage(page, search);
    }
}