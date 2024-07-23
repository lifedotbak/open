package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessGroup;
import com.spyker.commons.search.SysProcessGroupSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessGroupServiceTest extends BaseTest {

    @Autowired private SysProcessGroupService service;

    @Test
    public void get() {
        SysProcessGroup result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysProcessGroup add = new SysProcessGroup();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setGroupName("groupName");

        add.setProcessGroupSort(1);

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysProcessGroup update = new SysProcessGroup();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setGroupName("groupName");
        update.setProcessGroupSort(1);

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysProcessGroupSearch search = new SysProcessGroupSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setGroupName("groupName");
        search.setProcessGroupSort(1);

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysProcessGroup> page = new Page<>(1, 10);

        SysProcessGroupSearch search = new SysProcessGroupSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setGroupName("groupName");

        search.setProcessGroupSort(1);

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}