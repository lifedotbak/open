package com.spyker.commons.service;

import com.spyker.commons.entity.OpenProcessInstanceExecution;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.OpenProcessInstanceExecutionSearch;

/**
 * 流程执行id数据 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessInstanceExecutionService
        extends IService<OpenProcessInstanceExecution> {

    // @formatter:off

    List<OpenProcessInstanceExecution> query(OpenProcessInstanceExecutionSearch search);

    IPage<OpenProcessInstanceExecution> queryPage(
            IPage<OpenProcessInstanceExecution> page, OpenProcessInstanceExecutionSearch search);

    OpenProcessInstanceExecution get(String id);

    OpenProcessInstanceExecution insert(OpenProcessInstanceExecution openProcessInstanceExecution);

    OpenProcessInstanceExecution update(OpenProcessInstanceExecution openProcessInstanceExecution);

    boolean delete(String id);
}