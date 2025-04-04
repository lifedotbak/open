package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysRoleMenu;
import com.spyker.commons.search.SysRoleMenuSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色和菜单关联表 Mapper 接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-22
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<SysRoleMenu> query(SysRoleMenuSearch search);

    IPage<SysRoleMenu> queryPage(IPage<SysRoleMenu> page, SysRoleMenuSearch search);
}
