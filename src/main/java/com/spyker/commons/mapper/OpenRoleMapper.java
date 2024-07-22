package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenRole;
import com.spyker.commons.search.OpenRoleSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenRoleMapper extends BaseMapper<OpenRole> {

    // @formatter:off

    List<OpenRole> query(OpenRoleSearch search);

    IPage<OpenRole> queryPage(IPage<OpenRole> page, OpenRoleSearch search);
}