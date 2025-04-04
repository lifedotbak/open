package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceExecution;
import com.spyker.commons.search.SysProcessInstanceExecutionSearch;

import java.util.List;

/**
 * 流程执行id数据 服务接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
public interface SysProcessInstanceExecutionService extends IService<SysProcessInstanceExecution> {

    List<SysProcessInstanceExecution> query(SysProcessInstanceExecutionSearch search);

    IPage<SysProcessInstanceExecution> queryPage(
            IPage<SysProcessInstanceExecution> page, SysProcessInstanceExecutionSearch search);

    SysProcessInstanceExecution get(String id);

    SysProcessInstanceExecution insert(SysProcessInstanceExecution sysProcessInstanceExecution);

    SysProcessInstanceExecution update(SysProcessInstanceExecution sysProcessInstanceExecution);

    boolean delete(String id);
}
