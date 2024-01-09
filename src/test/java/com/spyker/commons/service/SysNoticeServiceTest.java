package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysNotice;
import com.spyker.commons.search.SysNoticeSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通知公告表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Slf4j
public class SysNoticeServiceTest extends BaseTest {

    @Autowired private SysNoticeService service;

    @Test
    public void get() {

        SysNotice result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysNotice add = new SysNotice();

        add.setNoticeTitle("noticeTitle");

        add.setNoticeType("noticeType");

        add.setStatus("status");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        service.insert(add);
    }

    @Test
    public void update() {
        SysNotice update = new SysNotice();

        update.setNoticeId("noticeId");

        update.setNoticeTitle("noticeTitle");

        update.setNoticeType("noticeType");

        update.setStatus("status");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        service.update(update);
    }

    @Test
    public void query() {
        SysNoticeSearch search = new SysNoticeSearch();

        search.setNoticeTitle("noticeTitle");
        search.setNoticeType("noticeType");
        search.setStatus("status");
        search.setCreateBy("createBy");
        search.setUpdateBy("updateBy");
        search.setRemark("remark");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysNotice> page = new Page<>(1, 10);

        SysNoticeSearch search = new SysNoticeSearch();

        search.setNoticeTitle("noticeTitle");

        search.setNoticeType("noticeType");

        search.setStatus("status");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.queryPage(page, search);
    }
}