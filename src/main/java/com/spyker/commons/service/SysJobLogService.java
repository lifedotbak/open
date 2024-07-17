package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysJobLog;
import com.spyker.commons.search.SysJobLogSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/**
 * 定时任务调度日志表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysJobLogService extends IService<SysJobLog> {

    List<SysJobLog> query(SysJobLogSearch search);

    IPage<SysJobLog> queryPage(IPage<SysJobLog> page, SysJobLogSearch search);

    SysJobLog get(String id);

    RestResponse<?> insert(SysJobLog sysJobLog);

    RestResponse<?> update(SysJobLog sysJobLog);

    RestResponse<?> delete(String id);
}