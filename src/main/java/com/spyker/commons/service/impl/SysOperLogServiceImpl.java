package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysOperLog;
import com.spyker.commons.mapper.SysOperLogMapper;
import com.spyker.commons.search.SysOperLogSearch;
import com.spyker.commons.service.SysOperLogService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {

    private final SysOperLogMapper sysOperLogMapper;

    @Override
    public List<SysOperLog> query(SysOperLogSearch search) {
        List<SysOperLog> SysOperLogList = sysOperLogMapper.query(search);

        return SysOperLogList;
    }

    @Override
    public IPage<SysOperLog> queryPage(IPage<SysOperLog> page, SysOperLogSearch search) {
        page = sysOperLogMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysOperLog get(String id) {
        SysOperLog SysOperLog = getById(id);

        return SysOperLog;
    }

    @Override
    public RestResponse<?> insert(SysOperLog SysOperLog) {
        save(SysOperLog);

        return RestResponse.success(SysOperLog);
    }

    @Override
    public RestResponse<?> update(SysOperLog SysOperLog) {
        updateById(SysOperLog);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}