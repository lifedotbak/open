package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessInstanceOperRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessInstanceOperRecord;
import com.spyker.commons.service.OpenProcessInstanceOperRecordService;

import com.spyker.commons.search.OpenProcessInstanceOperRecordSearch;
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
public class OpenProcessInstanceOperRecordServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessInstanceOperRecordService service;

    @Test
    public void get() {
        OpenProcessInstanceOperRecord result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessInstanceOperRecord add = new OpenProcessInstanceOperRecord();

        add.setUserId("userId");

        add.setFlowId("flowId");

        add.setNodeId("nodeId");

        add.setNodeName("nodeName");

        add.setProcessInstanceId("processInstanceId");

        add.setComment("comment");

        add.setOperType("operType");

        add.setOperDesc("operDesc");

        add.setImageList("imageList");

        add.setFileList("fileList");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessInstanceOperRecord update = new OpenProcessInstanceOperRecord();

        update.setId("id");

        update.setUserId("userId");

        update.setFlowId("flowId");

        update.setNodeId("nodeId");

        update.setNodeName("nodeName");

        update.setProcessInstanceId("processInstanceId");

        update.setComment("comment");

        update.setOperType("operType");

        update.setOperDesc("operDesc");

        update.setImageList("imageList");

        update.setFileList("fileList");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessInstanceOperRecordSearch search = new OpenProcessInstanceOperRecordSearch();

        search.setUserId("userId");

        search.setFlowId("flowId");

        search.setNodeId("nodeId");

        search.setNodeName("nodeName");

        search.setProcessInstanceId("processInstanceId");

        search.setComment("comment");

        search.setOperType("operType");

        search.setOperDesc("operDesc");

        search.setImageList("imageList");

        search.setFileList("fileList");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessInstanceOperRecord> page = new Page<>(1, 10);

        OpenProcessInstanceOperRecordSearch search = new OpenProcessInstanceOperRecordSearch();

        search.setUserId("userId");

        search.setFlowId("flowId");

        search.setNodeId("nodeId");

        search.setNodeName("nodeName");

        search.setProcessInstanceId("processInstanceId");

        search.setComment("comment");

        search.setOperType("operType");

        search.setOperDesc("operDesc");

        search.setImageList("imageList");

        search.setFileList("fileList");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}