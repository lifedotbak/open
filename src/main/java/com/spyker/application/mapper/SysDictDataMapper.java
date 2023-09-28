package com.spyker.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.application.entity.SysDictData;
import com.spyker.application.search.SysDictDataSearch;

import java.util.List;

/**
 * <p>
 * 字典数据表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    List<SysDictData> query(SysDictDataSearch search);

    IPage<SysDictData> queryPage(IPage<SysDictData> page, SysDictDataSearch search);

}