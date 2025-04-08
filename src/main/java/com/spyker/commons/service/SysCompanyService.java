package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysCompany;
import com.spyker.commons.search.SysCompanySearch;

import java.util.List;

/** 公司表 服务类 */
public interface SysCompanyService extends IService<SysCompany> {

    boolean delete(String id);

    SysCompany get(String id);

    boolean insert(SysCompany sysCompany);

    List<SysCompany> query(SysCompanySearch search);

    IPage<SysCompany> queryPage(IPage<SysCompany> page, SysCompanySearch search);

    boolean update(SysCompany sysCompany);
}
