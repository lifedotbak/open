package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysJobLog;
import com.spyker.commons.search.SysJobLogSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 定时任务调度日志表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@Slf4j
public class SysJobLogServiceTest extends BaseTest {

    @Autowired
    private SysJobLogService service;

    @Test
    public void get() {

        SysJobLog result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysJobLog add = new SysJobLog();

        add.setJobName("jobName");

        add.setJobGroup("jobGroup");

        add.setInvokeTarget("invokeTarget");

        add.setJobMessage("jobMessage");

        add.setStatus("status");

        add.setExceptionInfo("exceptionInfo");

        service.insert(add);
    }

    @Test
    public void update() {
        SysJobLog update = new SysJobLog();

        update.setJobLogId("jobLogId");

        update.setJobName("jobName");

        update.setJobGroup("jobGroup");

        update.setInvokeTarget("invokeTarget");

        update.setJobMessage("jobMessage");

        update.setStatus("status");

        update.setExceptionInfo("exceptionInfo");

        service.update(update);
    }

    @Test
    public void query() {
        SysJobLogSearch search = new SysJobLogSearch();

        search.setJobName("jobName");
        search.setJobGroup("jobGroup");
        search.setInvokeTarget("invokeTarget");
        search.setJobMessage("jobMessage");
        search.setStatus("status");
        search.setExceptionInfo("exceptionInfo");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysJobLog> page = new Page<>(1, 10);

        SysJobLogSearch search = new SysJobLogSearch();

        search.setJobName("jobName");

        search.setJobGroup("jobGroup");

        search.setInvokeTarget("invokeTarget");

        search.setJobMessage("jobMessage");

        search.setStatus("status");

        search.setExceptionInfo("exceptionInfo");

        service.queryPage(page, search);
    }

}