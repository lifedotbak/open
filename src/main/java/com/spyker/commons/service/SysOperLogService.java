package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysOperLog;
import com.spyker.commons.search.SysOperLogSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/** 操作日志记录 服务类 */
public interface SysOperLogService extends IService<SysOperLog> {

    RestResponse<?> delete(String id);

    SysOperLog get(String id);

    RestResponse<?> insert(SysOperLog sysOperLog);

    List<SysOperLog> query(SysOperLogSearch search);

    IPage<SysOperLog> queryPage(IPage<SysOperLog> page, SysOperLogSearch search);

    RestResponse<?> update(SysOperLog sysOperLog);
}
