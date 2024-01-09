package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysJobLog;
import com.spyker.commons.search.SysJobLogSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 定时任务调度日志表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Mapper
public interface SysJobLogMapper extends BaseMapper<SysJobLog> {

    List<SysJobLog> query(SysJobLogSearch search);

    IPage<SysJobLog> queryPage(IPage<SysJobLog> page, SysJobLogSearch search);
}