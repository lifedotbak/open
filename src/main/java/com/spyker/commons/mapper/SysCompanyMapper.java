package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spyker.commons.search.SysCompanySearch;

/**
 * <p>
 * 公司表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-12-25
 */
@Mapper
public interface SysCompanyMapper extends BaseMapper<SysCompany> {

	List<SysCompany> query(SysCompanySearch search);

	IPage<SysCompany> queryPage(IPage<SysCompany> page, SysCompanySearch search);

}