package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessInstanceExecution;
import com.spyker.commons.search.OpenProcessInstanceExecutionSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程执行id数据 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessInstanceExecutionMapper
        extends BaseMapper<OpenProcessInstanceExecution> {

    // @formatter:off

    List<OpenProcessInstanceExecution> query(OpenProcessInstanceExecutionSearch search);

    IPage<OpenProcessInstanceExecution> queryPage(
            IPage<OpenProcessInstanceExecution> page, OpenProcessInstanceExecutionSearch search);
}