package com.spyker.commons.mapper;

import com.spyker.commons.entity.OpenProcessMain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.spyker.commons.search.OpenProcessMainSearch;

/**
 * 流程主表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessMainMapper extends BaseMapper<OpenProcessMain> {

    // @formatter:off

    List<OpenProcessMain> query(OpenProcessMainSearch search);

    IPage<OpenProcessMain> queryPage(IPage<OpenProcessMain> page, OpenProcessMainSearch search);
}