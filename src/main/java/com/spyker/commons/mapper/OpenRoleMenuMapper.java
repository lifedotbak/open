package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenRoleMenu;
import com.spyker.commons.search.OpenRoleMenuSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色和菜单关联表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenRoleMenuMapper extends BaseMapper<OpenRoleMenu> {

    // @formatter:off

    List<OpenRoleMenu> query(OpenRoleMenuSearch search);

    IPage<OpenRoleMenu> queryPage(IPage<OpenRoleMenu> page, OpenRoleMenuSearch search);
}