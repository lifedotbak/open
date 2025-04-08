package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessInstanceRecord;
import com.spyker.commons.search.SysProcessInstanceRecordSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 流程记录 服务测试类 */
@Slf4j
public class SysProcessInstanceRecordServiceTest extends BaseTest {

    @Autowired private SysProcessInstanceRecordService service;

    @Test
    public void add() {
        SysProcessInstanceRecord add = new SysProcessInstanceRecord();

        add.setName("name");

        add.setLogo("logo");

        add.setUserId("userId");

        add.setMainDeptId("mainDeptId");

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setFlowId("flowId");

        add.setProcessInstanceId("processInstanceId");

        add.setProcessInstanceBizCode("processInstanceBizCode");

        add.setProcessInstanceBizKey("processInstanceBizKey");

        add.setFormData("formData");

        add.setGroupId("groupId");

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
    public void delete() {
        service.delete("1");
    }

    @Test
    public void get() {
        SysProcessInstanceRecord result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void query() {
        SysProcessInstanceRecordSearch search = new SysProcessInstanceRecordSearch();

        search.setName("name");

        search.setLogo("logo");

        search.setUserId("userId");

        search.setMainDeptId("mainDeptId");
        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setProcessInstanceBizCode("processInstanceBizCode");

        search.setProcessInstanceBizKey("processInstanceBizKey");

        search.setFormData("formData");

        search.setGroupId("groupId");

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
        IPage<SysProcessInstanceRecord> page = new Page<>(1, 10);

        SysProcessInstanceRecordSearch search = new SysProcessInstanceRecordSearch();

        search.setName("name");

        search.setLogo("logo");

        search.setUserId("userId");

        search.setMainDeptId("mainDeptId");

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setProcessInstanceBizCode("processInstanceBizCode");

        search.setProcessInstanceBizKey("processInstanceBizKey");

        search.setFormData("formData");

        search.setGroupId("groupId");

        search.setGroupName("groupName");

        search.setStatus(1);

        search.setParentProcessInstanceId("parentProcessInstanceId");

        search.setProcess("process");

        search.setResult(1);

        search.setTenantId("tenantId");

        search.setParentProcessNodeExecutionId("parentProcessNodeExecutionId");

        service.queryPage(page, search);
    }

    @Test
    public void update() {
        SysProcessInstanceRecord update = new SysProcessInstanceRecord();

        update.setId("id");

        update.setName("name");

        update.setLogo("logo");

        update.setUserId("userId");

        update.setMainDeptId("mainDeptId");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setFlowId("flowId");

        update.setProcessInstanceId("processInstanceId");

        update.setProcessInstanceBizCode("processInstanceBizCode");

        update.setProcessInstanceBizKey("processInstanceBizKey");

        update.setFormData("formData");

        update.setGroupId("groupId");

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
}
