package com.spyker.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.application.entity.SysJobLog;
import com.spyker.application.mapper.SysJobLogMapper;
import com.spyker.application.search.SysJobLogSearch;
import com.spyker.application.service.SysJobLogService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 定时任务调度日志表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLog> implements SysJobLogService {

    private final SysJobLogMapper sysJobLogMapper;

    @Override
    public List<SysJobLog> query(SysJobLogSearch search) {
        List<SysJobLog> SysJobLogList = sysJobLogMapper.query(search);

        return SysJobLogList;
    }

    @Override
    public IPage<SysJobLog> queryPage(IPage<SysJobLog> page, SysJobLogSearch search) {
        page = sysJobLogMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysJobLog get(String id) {
        SysJobLog SysJobLog = getById(id);

        return SysJobLog;
    }

    @Override
    public RestResponse<?> insert(SysJobLog SysJobLog) {
        save(SysJobLog);

        return RestResponse.success(SysJobLog);
    }

    @Override
    public RestResponse<?> update(SysJobLog SysJobLog) {
        updateById(SysJobLog);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}