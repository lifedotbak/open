package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.BaseTest;
import com.spyker.application.entity.SysUserPost;
import com.spyker.application.search.SysUserPostSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户与岗位关联表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@Slf4j
public class SysUserPostServiceTest extends BaseTest {

    @Autowired
    private SysUserPostService service;

    @Test
    public void get() {

        SysUserPost result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysUserPost add = new SysUserPost();

        service.insert(add);
    }

    @Test
    public void update() {
        SysUserPost update = new SysUserPost();

        update.setUserId("userId");

        update.setPostId("postId");

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