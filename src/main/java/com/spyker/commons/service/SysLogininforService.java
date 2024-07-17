package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysLogininfor;
import com.spyker.commons.search.SysLogininforSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/**
 * 系统访问记录 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysLogininforService extends IService<SysLogininfor> {

    List<SysLogininfor> query(SysLogininforSearch search);

    IPage<SysLogininfor> queryPage(IPage<SysLogininfor> page, SysLogininforSearch search);

    SysLogininfor get(String id);

    RestResponse<?> insert(SysLogininfor sysLogininfor);

    RestResponse<?> update(SysLogininfor sysLogininfor);

    RestResponse<?> delete(String id);
}