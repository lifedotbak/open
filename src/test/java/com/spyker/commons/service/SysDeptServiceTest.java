package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysDept;
import com.spyker.commons.search.SysDeptSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 部门表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysDeptServiceTest extends BaseTest {

    @Autowired private SysDeptService service;

    @Test
    public void get() {
        SysDept result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysDept add = new SysDept();

        add.setName("name");

        add.setParentId("parentId");

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setStatus(1);

        add.setDeptSort(1);

        add.setTenantId("tenantId");

        add.setAncestors("ancestors");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysDept update = new SysDept();

        update.setId("id");

        update.setName("name");

        update.setParentId("parentId");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");
        update.setStatus(1);

        update.setDeptSort(1);

        update.setTenantId("tenantId");

        update.setAncestors("ancestors");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysDeptSearch search = new SysDeptSearch();

        search.setName("name");

        search.setParentId("parentId");
        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");
        search.setStatus(1);

        search.setDeptSort(1);

        search.setTenantId("tenantId");

        search.setAncestors("ancestors");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysDept> page = new Page<>(1, 10);

        SysDeptSearch search = new SysDeptSearch();

        search.setName("name");

        search.setParentId("parentId");

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setStatus(1);

        search.setDeptSort(1);

        search.setTenantId("tenantId");

        search.setAncestors("ancestors");

        service.queryPage(page, search);
    }
}