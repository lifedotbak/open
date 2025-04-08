package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysLoginInfo;
import com.spyker.commons.search.SysLoginInfoSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/** 系统访问记录 服务类 */
public interface SysLoginInfoService extends IService<SysLoginInfo> {

    RestResponse<?> delete(String id);

    SysLoginInfo get(String id);

    RestResponse<?> insert(SysLoginInfo sysLogininfor);

    List<SysLoginInfo> query(SysLoginInfoSearch search);

    IPage<SysLoginInfo> queryPage(IPage<SysLoginInfo> page, SysLoginInfoSearch search);

    RestResponse<?> update(SysLoginInfo sysLogininfor);
}
