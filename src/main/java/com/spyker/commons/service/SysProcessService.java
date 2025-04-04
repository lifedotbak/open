package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcess;
import com.spyker.commons.search.SysProcessSearch;

import java.util.List;

/**
 * 服务接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
public interface SysProcessService extends IService<SysProcess> {

    List<SysProcess> query(SysProcessSearch search);

    IPage<SysProcess> queryPage(IPage<SysProcess> page, SysProcessSearch search);

    SysProcess get(String id);

    SysProcess insert(SysProcess sysProcess);

    SysProcess update(SysProcess sysProcess);

    boolean delete(String id);
}
