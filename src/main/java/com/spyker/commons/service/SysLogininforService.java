package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysLogininfor;
import com.spyker.commons.search.SysLogininforSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 系统访问记录 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysLogininforService extends IService<SysLogininfor> {

    List<SysLogininfor> query(SysLogininforSearch search);

    IPage<SysLogininfor> queryPage(IPage<SysLogininfor> page, SysLogininforSearch search);

    SysLogininfor get(String id);

    RestResponse<?> insert(SysLogininfor SysLogininfor);

    RestResponse<?> update(SysLogininfor SysLogininfor);

    RestResponse<?> delete(String id);

}