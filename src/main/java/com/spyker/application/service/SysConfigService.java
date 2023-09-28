package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.SysConfig;
import com.spyker.application.search.SysConfigSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysConfigService extends IService<SysConfig> {

    List<SysConfig> query(SysConfigSearch search);

    IPage<SysConfig> queryPage(IPage<SysConfig> page, SysConfigSearch search);

    SysConfig get(String id);

    RestResponse<?> insert(SysConfig SysConfig);

    RestResponse<?> update(SysConfig SysConfig);

    RestResponse<?> delete(String id);

}