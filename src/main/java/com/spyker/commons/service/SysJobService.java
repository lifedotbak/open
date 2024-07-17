package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysJob;
import com.spyker.commons.search.SysJobSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/**
 * 定时任务调度表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysJobService extends IService<SysJob> {

    List<SysJob> query(SysJobSearch search);

    IPage<SysJob> queryPage(IPage<SysJob> page, SysJobSearch search);

    SysJob get(String id);

    RestResponse<?> insert(SysJob sysJob);

    RestResponse<?> update(SysJob sysJob);

    RestResponse<?> delete(String id);
}