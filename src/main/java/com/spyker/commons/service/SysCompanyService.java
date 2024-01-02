package com.spyker.commons.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysCompany;
import com.spyker.commons.search.SysCompanySearch;

/**
 * <p>
 * 公司表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-12-25
 */
public interface SysCompanyService extends IService<SysCompany> {

	List<SysCompany> query(SysCompanySearch search);

	IPage<SysCompany> queryPage(IPage<SysCompany> page, SysCompanySearch search);

	SysCompany get(String id);

	boolean insert(SysCompany SysCompany);

	boolean update(SysCompany SysCompany);

	boolean delete(String id);

}