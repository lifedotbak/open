package com.spyker.commons.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysOperLog;
import com.spyker.commons.search.SysOperLogSearch;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {

	List<SysOperLog> query(SysOperLogSearch search);

	IPage<SysOperLog> queryPage(IPage<SysOperLog> page, SysOperLogSearch search);

}