package com.spyker.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.application.entity.SysRoleMenu;
import com.spyker.application.search.SysRoleMenuSearch;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<SysRoleMenu> query(SysRoleMenuSearch search);

    IPage<SysRoleMenu> queryPage(IPage<SysRoleMenu> page, SysRoleMenuSearch search);

}