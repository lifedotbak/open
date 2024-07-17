package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysExceptionLog;
import com.spyker.commons.search.SysExceptionLogSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 操作日志记录 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-16
 */
@Slf4j
public class SysExceptionLogServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private SysExceptionLogService service;

    @Test
    public void get() {
        SysExceptionLog result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysExceptionLog add = new SysExceptionLog();

        add.setExpUrl("expUrl");

        add.setExpParams("expParams");

        add.setExpType("expType");

        add.setExpController("expController");

        add.setExpMethod("expMethod");

        add.setExpDetail("expDetail");

        add.setUpdateBy("updateBy");

        add.setCreateBy("createBy");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysExceptionLog update = new SysExceptionLog();

        update.setId("id");

        update.setExpUrl("expUrl");

        update.setExpParams("expParams");

        update.setExpType("expType");

        update.setExpController("expController");

        update.setExpMethod("expMethod");

        update.setExpDetail("expDetail");

        update.setUpdateBy("updateBy");

        update.setCreateBy("createBy");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysExceptionLogSearch search = new SysExceptionLogSearch();

        search.setExpUrl("expUrl");

        search.setExpParams("expParams");

        search.setExpType("expType");

        search.setExpController("expController");

        search.setExpMethod("expMethod");

        search.setExpDetail("expDetail");

        search.setUpdateBy("updateBy");

        search.setCreateBy("createBy");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysExceptionLog> page = new Page<>(1, 10);

        SysExceptionLogSearch search = new SysExceptionLogSearch();

        search.setExpUrl("expUrl");

        search.setExpParams("expParams");

        search.setExpType("expType");

        search.setExpController("expController");

        search.setExpMethod("expMethod");

        search.setExpDetail("expDetail");

        search.setUpdateBy("updateBy");

        search.setCreateBy("createBy");

        service.queryPage(page, search);
    }
}