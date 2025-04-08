package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessGroup;
import com.spyker.commons.search.SysProcessGroupSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** Mapper 接口 */
@Mapper
public interface SysProcessGroupMapper extends BaseMapper<SysProcessGroup> {

    List<SysProcessGroup> query(SysProcessGroupSearch search);

    IPage<SysProcessGroup> queryPage(IPage<SysProcessGroup> page, SysProcessGroupSearch search);
}
