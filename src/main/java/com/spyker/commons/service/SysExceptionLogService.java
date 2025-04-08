package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysExceptionLog;
import com.spyker.commons.search.SysExceptionLogSearch;

import java.util.List;

/** 操作日志记录 服务接口 */
public interface SysExceptionLogService extends IService<SysExceptionLog> {

    boolean delete(String id);

    SysExceptionLog get(String id);

    SysExceptionLog insert(SysExceptionLog sysExceptionLog);

    List<SysExceptionLog> query(SysExceptionLogSearch search);

    IPage<SysExceptionLog> queryPage(IPage<SysExceptionLog> page, SysExceptionLogSearch search);

    SysExceptionLog update(SysExceptionLog sysExceptionLog);
}
