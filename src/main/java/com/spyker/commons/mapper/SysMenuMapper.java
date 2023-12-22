package com.spyker.commons.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysMenu;
import com.spyker.commons.search.SysMenuSearch;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	List<SysMenu> query(SysMenuSearch search);

	IPage<SysMenu> queryPage(IPage<SysMenu> page, SysMenuSearch search);

}