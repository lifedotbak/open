package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysDictData;
import com.spyker.commons.search.SysDictDataSearch;

import java.util.List;

/**
 * 字典数据表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysDictDataService extends IService<SysDictData> {

    List<SysDictData> query(SysDictDataSearch search);

    IPage<SysDictData> queryPage(IPage<SysDictData> page, SysDictDataSearch search);

    SysDictData get(String id);

    SysDictData insert(SysDictData sysDictData);

    SysDictData update(SysDictData sysDictData);

    void delete(String id);
}