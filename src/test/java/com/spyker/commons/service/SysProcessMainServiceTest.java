package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessMain;
import com.spyker.commons.search.SysProcessMainSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程主表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessMainServiceTest extends BaseTest {

    @Autowired private SysProcessMainService service;

    @Test
    public void get() {
        SysProcessMain result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysProcessMain add = new SysProcessMain();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setName("name");

        add.setLogo("logo");

        add.setGroupId("groupId");

        add.setProcessMainSort(1);

        add.setUniqueId("uniqueId");

        add.setRangeShow("rangeShow");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysProcessMain update = new SysProcessMain();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setName("name");

        update.setLogo("logo");

        update.setGroupId("groupId");
        update.setProcessMainSort(1);

        update.setUniqueId("uniqueId");

        update.setRangeShow("rangeShow");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysProcessMainSearch search = new SysProcessMainSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setName("name");

        search.setLogo("logo");

        search.setGroupId("groupId");
        search.setProcessMainSort(1);

        search.setUniqueId("uniqueId");

        search.setRangeShow("rangeShow");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysProcessMain> page = new Page<>(1, 10);

        SysProcessMainSearch search = new SysProcessMainSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setName("name");

        search.setLogo("logo");

        search.setGroupId("groupId");

        search.setProcessMainSort(1);

        search.setUniqueId("uniqueId");

        search.setRangeShow("rangeShow");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}