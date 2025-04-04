package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysDictData;
import com.spyker.commons.search.SysDictDataSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数据表 Mapper 接口
 *
 * @author 121232224@qq.com
 * @since 2023-09-28
 */
@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    List<SysDictData> query(SysDictDataSearch search);

    IPage<SysDictData> queryPage(IPage<SysDictData> page, SysDictDataSearch search);
}
