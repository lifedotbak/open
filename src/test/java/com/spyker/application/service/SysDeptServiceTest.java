package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.BaseTest;
import com.spyker.application.entity.SysDept;
import com.spyker.application.search.SysDeptSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@Slf4j
public class SysDeptServiceTest extends BaseTest {

    @Autowired
    private SysDeptService service;

    @Test
    public void get() {

        SysDept result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysDept add = new SysDept();

        add.setParentId("parentId");

        add.setAncestors("ancestors");

        add.setDeptName("deptName");

        add.setOrderNum(1);

        add.setLeader("leader");

        add.setPhone("phone");

        add.setEmail("email");

        add.setStatus("status");

        add.setDelFlag("delFlag");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        service.insert(add);
    }

    @Test
    public void update() {
        SysDept update = new SysDept();

        update.setDeptId("deptId");

        update.setParentId("parentId");

        update.setAncestors("ancestors");

        update.setDeptName("deptName");
        update.setOrderNum(1);

        update.setLeader("leader");

        update.setPhone("phone");

        update.setEmail("email");

        update.setStatus("status");

        update.setDelFlag("delFlag");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        service.update(update);
    }

    @Test
    public void query() {
        SysDeptSearch search = new SysDeptSearch();

        search.setParentId("parentId");
        search.setAncestors("ancestors");
        search.setDeptName("deptName");
        search.setLeader("leader");
        search.setPhone("phone");
        search.setEmail("email");
        search.setStatus("status");
        search.setDelFlag("delFlag");
        search.setCreateBy("createBy");
        search.setUpdateBy("updateBy");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysDept> page = new Page<>(1, 10);

        SysDeptSearch search = new SysDeptSearch();

        search.setParentId("parentId");

        search.setAncestors("ancestors");

        search.setDeptName("deptName");

        search.setOrderNum(1);

        search.setLeader("leader");

        search.setPhone("phone");

        search.setEmail("email");

        search.setStatus("status");

        search.setDelFlag("delFlag");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        service.queryPage(page, search);
    }

}