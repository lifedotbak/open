package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysCompany;
import com.spyker.commons.mapper.SysCompanyMapper;
import com.spyker.commons.search.SysCompanySearch;
import com.spyker.commons.service.SysCompanyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 公司表 服务实现类 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysCompanyServiceImpl extends ServiceImpl<SysCompanyMapper, SysCompany>
        implements SysCompanyService {

    private final SysCompanyMapper sysCompanyMapper;

    @Override
    public boolean delete(String id) {
        return removeById(id);
    }

    @Override
    public SysCompany get(String id) {
        SysCompany result = getById(id);

        log.info("result-->{}", result);

        return result;
    }

    @Override
    public boolean insert(SysCompany sysCompany) {
        return save(sysCompany);
    }

    @Override
    public List<SysCompany> query(SysCompanySearch search) {

        List<SysCompany> result = sysCompanyMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<SysCompany> queryPage(IPage<SysCompany> page, SysCompanySearch search) {

        page = sysCompanyMapper.queryPage(page, search);
        log.info("page------>{}", page);

        return page;
    }

    @Override
    public boolean update(SysCompany sysCompany) {
        return updateById(sysCompany);
    }
}
