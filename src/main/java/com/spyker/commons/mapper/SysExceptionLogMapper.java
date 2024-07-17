package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysExceptionLog;
import com.spyker.commons.search.SysExceptionLogSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作日志记录 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-16
 */
@Mapper
public interface SysExceptionLogMapper extends BaseMapper<SysExceptionLog> {

    List<SysExceptionLog> query(SysExceptionLogSearch search);

    IPage<SysExceptionLog> queryPage(IPage<SysExceptionLog> page, SysExceptionLogSearch search);
}