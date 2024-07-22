package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenMenu;
import com.spyker.commons.search.OpenMenuSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenMenuMapper extends BaseMapper<OpenMenu> {

    // @formatter:off

    List<OpenMenu> query(OpenMenuSearch search);

    IPage<OpenMenu> queryPage(IPage<OpenMenu> page, OpenMenuSearch search);
}