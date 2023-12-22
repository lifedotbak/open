package com.spyker.commons.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysLogininfor;
import com.spyker.commons.search.SysLogininforSearch;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Mapper
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {

	List<SysLogininfor> query(SysLogininforSearch search);

	IPage<SysLogininfor> queryPage(IPage<SysLogininfor> page, SysLogininforSearch search);

}