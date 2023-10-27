package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysJobLog;
import com.spyker.commons.search.SysJobLogSearch;

import java.util.List;

/**
 * <p>
 * 定时任务调度日志表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysJobLogMapper extends BaseMapper<SysJobLog> {

    List<SysJobLog> query(SysJobLogSearch search);

    IPage<SysJobLog> queryPage(IPage<SysJobLog> page, SysJobLogSearch search);

}