package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysPost;
import com.spyker.commons.search.SysPostSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 岗位信息表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Slf4j
public class SysPostServiceTest extends BaseTest {

    @Autowired private SysPostService service;

    @Test
    public void get() {

        SysPost result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysPost add = new SysPost();

        add.setPostCode("postCode");

        add.setPostName("postName");

        add.setPostSort(1);

        add.setStatus("status");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        service.insert(add);
    }

    @Test
    public void update() {
        SysPost update = new SysPost();

        update.setPostId("postId");

        update.setPostCode("postCode");

        update.setPostName("postName");
        update.setPostSort(1);

        update.setStatus("status");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        service.update(update);
    }

    @Test
    public void query() {
        SysPostSearch search = new SysPostSearch();

        search.setPostCode("postCode");
        search.setPostName("postName");
        search.setStatus("status");
        search.setCreateBy("createBy");
        search.setUpdateBy("updateBy");
        search.setRemark("remark");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysPost> page = new Page<>(1, 10);

        SysPostSearch search = new SysPostSearch();

        search.setPostCode("postCode");

        search.setPostName("postName");

        search.setStatus("status");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.queryPage(page, search);
    }
}