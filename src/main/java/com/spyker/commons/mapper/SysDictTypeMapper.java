package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysDictType;
import com.spyker.commons.search.SysDictTypeSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 字典类型表 Mapper 接口 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    List<SysDictType> query(SysDictTypeSearch search);

    IPage<SysDictType> queryPage(IPage<SysDictType> page, SysDictTypeSearch search);
}
