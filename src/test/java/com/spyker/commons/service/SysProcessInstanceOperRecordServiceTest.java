package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessInstanceOperRecord;
import com.spyker.commons.search.SysProcessInstanceOperRecordSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程记录 服务测试类
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessInstanceOperRecordServiceTest extends BaseTest {

    @Autowired private SysProcessInstanceOperRecordService service;

    @Test
    public void get() {
        SysProcessInstanceOperRecord result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysProcessInstanceOperRecord add = new SysProcessInstanceOperRecord();

        add.setUserId("userId");

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

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
        SysProcessInstanceOperRecord update = new SysProcessInstanceOperRecord();

        update.setId("id");

        update.setUserId("userId");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

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
        SysProcessInstanceOperRecordSearch search = new SysProcessInstanceOperRecordSearch();

        search.setUserId("userId");
        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

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
        IPage<SysProcessInstanceOperRecord> page = new Page<>(1, 10);

        SysProcessInstanceOperRecordSearch search = new SysProcessInstanceOperRecordSearch();

        search.setUserId("userId");

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

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
