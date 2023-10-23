package com.spyker.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.base.entity.SysDictType;
import com.spyker.base.search.SysDictTypeSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysDictTypeService extends IService<SysDictType> {

    List<SysDictType> query(SysDictTypeSearch search);

    IPage<SysDictType> queryPage(IPage<SysDictType> page, SysDictTypeSearch search);

    SysDictType get(String id);

    RestResponse<?> insert(SysDictType SysDictType);

    RestResponse<?> update(SysDictType SysDictType);

    RestResponse<?> delete(String id);

}