package com.spyker.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.base.entity.SysNotice;
import com.spyker.base.mapper.SysNoticeMapper;
import com.spyker.base.search.SysNoticeSearch;
import com.spyker.base.service.SysNoticeService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 通知公告表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {

    private final SysNoticeMapper sysNoticeMapper;

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
    public SysNotice get(String id) {
        SysNotice SysNotice = getById(id);

        return SysNotice;
    }

    @Override
    public RestResponse<?> insert(SysNotice SysNotice) {
        save(SysNotice);

        return RestResponse.success(SysNotice);
    }

    @Override
    public RestResponse<?> update(SysNotice SysNotice) {
        updateById(SysNotice);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}