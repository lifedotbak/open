package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysUserPost;
import com.spyker.commons.search.SysUserPostSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户与岗位关联表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysUserPostServiceTest extends BaseTest {

    @Autowired private SysUserPostService service;

    @Test
    public void get() {
        SysUserPost result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysUserPost add = new SysUserPost();

        add.setUserId("userId");

        add.setPostId("postId");

        add.setTenantId("tenantId");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysUserPost update = new SysUserPost();

        update.setId("id");

        update.setUserId("userId");

        update.setPostId("postId");

        update.setTenantId("tenantId");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysUserPostSearch search = new SysUserPostSearch();

        search.setUserId("userId");

        search.setPostId("postId");

        search.setTenantId("tenantId");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysUserPost> page = new Page<>(1, 10);

        SysUserPostSearch search = new SysUserPostSearch();

        search.setUserId("userId");

        search.setPostId("postId");

        search.setTenantId("tenantId");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        service.queryPage(page, search);
    }
}