package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysJob;
import com.spyker.commons.mapper.SysJobMapper;
import com.spyker.commons.search.SysJobSearch;
import com.spyker.commons.service.SysJobService;
import com.spyker.framework.web.response.RestResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 定时任务调度表 服务实现类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

    private final SysJobMapper sysJobMapper;

    @Override
    public List<SysJob> query(SysJobSearch search) {
        List<SysJob> SysJobList = sysJobMapper.query(search);

        return SysJobList;
    }

    @Override
    public IPage<SysJob> queryPage(IPage<SysJob> page, SysJobSearch search) {
        page = sysJobMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysJob get(String id) {
        SysJob SysJob = getById(id);

        return SysJob;
    }

    @Override
    public RestResponse<?> insert(SysJob sysJob) {
        save(sysJob);

        return RestResponse.success(sysJob);
    }

    @Override
    public RestResponse<?> update(SysJob sysJob) {
        updateById(sysJob);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }
}