package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysJob;
import com.spyker.commons.search.SysJobSearch;

import java.util.List;

/**
 * <p>
 * 定时任务调度表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysJobMapper extends BaseMapper<SysJob> {

    List<SysJob> query(SysJobSearch search);

    IPage<SysJob> queryPage(IPage<SysJob> page, SysJobSearch search);

}