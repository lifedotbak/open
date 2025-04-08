package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysOssConfig;
import com.spyker.commons.search.SysOssConfigSearch;

import java.util.List;

/** 对象存储配置表 服务类 */
public interface SysOssConfigService extends IService<SysOssConfig> {

    boolean delete(String id);

    SysOssConfig get(String id);

    boolean insert(SysOssConfig sysOssConfig);

    List<SysOssConfig> query(SysOssConfigSearch search);

    IPage<SysOssConfig> queryPage(IPage<SysOssConfig> page, SysOssConfigSearch search);

    boolean update(SysOssConfig sysOssConfig);
}
