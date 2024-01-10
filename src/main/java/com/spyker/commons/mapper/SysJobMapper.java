package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysJob;
import com.spyker.commons.search.SysJobSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 定时任务调度表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Mapper
public interface SysJobMapper extends BaseMapper<SysJob> {

    List<SysJob> query(SysJobSearch search);

    IPage<SysJob> queryPage(IPage<SysJob> page, SysJobSearch search);
}