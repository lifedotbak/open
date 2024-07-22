package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.SysUserPostService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.SysUserPost;
import com.spyker.commons.service.SysUserPostService;

import com.spyker.commons.search.SysUserPostSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 用户与岗位关联表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysUserPostServiceTest extends BaseTest {

    // @formatter:off

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

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysUserPost update = new SysUserPost();

        update.setUserId("userId");

        update.setPostId("postId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysUserPostSearch search = new SysUserPostSearch();

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysUserPost> page = new Page<>(1, 10);

        SysUserPostSearch search = new SysUserPostSearch();

        service.queryPage(page, search);
    }
}