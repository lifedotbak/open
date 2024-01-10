package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysOperLog;
import com.spyker.commons.search.SysOperLogSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * 操作日志记录 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysOperLogService extends IService<SysOperLog> {

    List<SysOperLog> query(SysOperLogSearch search);

    IPage<SysOperLog> queryPage(IPage<SysOperLog> page, SysOperLogSearch search);

    SysOperLog get(String id);

    RestResponse<?> insert(SysOperLog SysOperLog);

    RestResponse<?> update(SysOperLog SysOperLog);

    RestResponse<?> delete(String id);
}