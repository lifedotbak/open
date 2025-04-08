package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysDictType;
import com.spyker.commons.search.SysDictTypeSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/** 字典类型表 服务类 */
public interface SysDictTypeService extends IService<SysDictType> {

    RestResponse<?> delete(String id);

    SysDictType get(String id);

    RestResponse<?> insert(SysDictType sysDictType);

    List<SysDictType> query(SysDictTypeSearch search);

    IPage<SysDictType> queryPage(IPage<SysDictType> page, SysDictTypeSearch search);

    RestResponse<?> update(SysDictType sysDictType);
}
