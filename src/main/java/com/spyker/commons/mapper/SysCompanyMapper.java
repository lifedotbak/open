package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysCompany;
import com.spyker.commons.search.SysCompanySearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 公司表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2023-12-25
 */
@Mapper
public interface SysCompanyMapper extends BaseMapper<SysCompany> {

    List<SysCompany> query(SysCompanySearch search);

    IPage<SysCompany> queryPage(IPage<SysCompany> page, SysCompanySearch search);
}