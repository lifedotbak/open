package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysNotice;
import com.spyker.commons.search.SysNoticeSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/** 通知公告表 服务类 */
public interface SysNoticeService extends IService<SysNotice> {

    RestResponse<?> delete(String id);

    SysNotice get(String id);

    RestResponse<?> insert(SysNotice sysNotice);

    List<SysNotice> query(SysNoticeSearch search);

    IPage<SysNotice> queryPage(IPage<SysNotice> page, SysNoticeSearch search);

    RestResponse<?> update(SysNotice sysNotice);
}
