package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenUserRole;
import com.spyker.commons.search.OpenUserRoleSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户-角色 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenUserRoleMapper extends BaseMapper<OpenUserRole> {

    // @formatter:off

    List<OpenUserRole> query(OpenUserRoleSearch search);

    IPage<OpenUserRole> queryPage(IPage<OpenUserRole> page, OpenUserRoleSearch search);
}