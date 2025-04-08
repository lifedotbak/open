package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysNotice;
import com.spyker.commons.mapper.SysNoticeMapper;
import com.spyker.commons.search.SysNoticeSearch;
import com.spyker.commons.service.SysNoticeService;
import com.spyker.framework.web.response.RestResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 通知公告表 服务实现类 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice>
        implements SysNoticeService {

    private final SysNoticeMapper sysNoticeMapper;

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

    @Override
    public SysNotice get(String id) {
        SysNotice SysNotice = getById(id);

        return SysNotice;
    }

    @Override
    public RestResponse<?> insert(SysNotice sysNotice) {
        save(sysNotice);

        return RestResponse.success(sysNotice);
    }

    @Override
    public List<SysNotice> query(SysNoticeSearch search) {
        List<SysNotice> SysNoticeList = sysNoticeMapper.query(search);

        return SysNoticeList;
    }

    @Override
    public IPage<SysNotice> queryPage(IPage<SysNotice> page, SysNoticeSearch search) {
        page = sysNoticeMapper.queryPage(page, search);

        return page;
    }

    @Override
    public RestResponse<?> update(SysNotice sysNotice) {
        updateById(sysNotice);

        return RestResponse.success();
    }
}
