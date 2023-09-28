package com.spyker.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.application.entity.SysOperLog;
import com.spyker.application.search.SysOperLogSearch;

import java.util.List;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {

    List<SysOperLog> query(SysOperLogSearch search);

    IPage<SysOperLog> queryPage(IPage<SysOperLog> page, SysOperLogSearch search);

}