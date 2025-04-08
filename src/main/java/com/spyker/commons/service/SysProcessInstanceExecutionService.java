package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceExecution;
import com.spyker.commons.search.SysProcessInstanceExecutionSearch;

import java.util.List;

/** 流程执行id数据 服务接口 */
public interface SysProcessInstanceExecutionService extends IService<SysProcessInstanceExecution> {

    boolean delete(String id);

    SysProcessInstanceExecution get(String id);

    SysProcessInstanceExecution insert(SysProcessInstanceExecution sysProcessInstanceExecution);

    List<SysProcessInstanceExecution> query(SysProcessInstanceExecutionSearch search);

    IPage<SysProcessInstanceExecution> queryPage(
            IPage<SysProcessInstanceExecution> page, SysProcessInstanceExecutionSearch search);

    SysProcessInstanceExecution update(SysProcessInstanceExecution sysProcessInstanceExecution);
}
