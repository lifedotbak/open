package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysOperLog;
import com.spyker.commons.search.SysOperLogSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 操作日志记录 Mapper 接口 */
@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {

    List<SysOperLog> query(SysOperLogSearch search);

    IPage<SysOperLog> queryPage(IPage<SysOperLog> page, SysOperLogSearch search);
}
