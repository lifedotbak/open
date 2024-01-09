package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysNotice;
import com.spyker.commons.search.SysNoticeSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * 通知公告表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysNoticeService extends IService<SysNotice> {

    List<SysNotice> query(SysNoticeSearch search);

    IPage<SysNotice> queryPage(IPage<SysNotice> page, SysNoticeSearch search);

    SysNotice get(String id);

    RestResponse<?> insert(SysNotice SysNotice);

    RestResponse<?> update(SysNotice SysNotice);

    RestResponse<?> delete(String id);
}