package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessMain;
import com.spyker.commons.search.SysProcessMainSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程主表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Mapper
public interface SysProcessMainMapper extends BaseMapper<SysProcessMain> {

    List<SysProcessMain> query(SysProcessMainSearch search);

    IPage<SysProcessMain> queryPage(IPage<SysProcessMain> page, SysProcessMainSearch search);
}