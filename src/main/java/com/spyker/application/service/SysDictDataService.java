package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.SysDictData;
import com.spyker.application.search.SysDictDataSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysDictDataService extends IService<SysDictData> {

    List<SysDictData> query(SysDictDataSearch search);

    IPage<SysDictData> queryPage(IPage<SysDictData> page, SysDictDataSearch search);

    SysDictData get(String id);

    RestResponse<?> insert(SysDictData SysDictData);

    RestResponse<?> update(SysDictData SysDictData);

    RestResponse<?> delete(String id);

}