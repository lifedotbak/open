package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.SysJobLog;
import com.spyker.application.search.SysJobLogSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 定时任务调度日志表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysJobLogService extends IService<SysJobLog> {

    List<SysJobLog> query(SysJobLogSearch search);

    IPage<SysJobLog> queryPage(IPage<SysJobLog> page, SysJobLogSearch search);

    SysJobLog get(String id);

    RestResponse<?> insert(SysJobLog SysJobLog);

    RestResponse<?> update(SysJobLog SysJobLog);

    RestResponse<?> delete(String id);

}