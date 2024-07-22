package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessGroup;
import com.spyker.commons.search.OpenProcessGroupSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessGroupMapper extends BaseMapper<OpenProcessGroup> {

    // @formatter:off

    List<OpenProcessGroup> query(OpenProcessGroupSearch search);

    IPage<OpenProcessGroup> queryPage(IPage<OpenProcessGroup> page, OpenProcessGroupSearch search);
}