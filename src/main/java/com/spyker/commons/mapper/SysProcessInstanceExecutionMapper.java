package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessInstanceExecution;
import com.spyker.commons.search.SysProcessInstanceExecutionSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程执行id数据 Mapper 接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Mapper
public interface SysProcessInstanceExecutionMapper extends BaseMapper<SysProcessInstanceExecution> {

    List<SysProcessInstanceExecution> query(SysProcessInstanceExecutionSearch search);

    IPage<SysProcessInstanceExecution> queryPage(
            IPage<SysProcessInstanceExecution> page, SysProcessInstanceExecutionSearch search);
}
