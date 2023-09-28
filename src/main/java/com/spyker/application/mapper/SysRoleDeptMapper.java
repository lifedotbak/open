package com.spyker.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.application.entity.SysRoleDept;
import com.spyker.application.search.SysRoleDeptSearch;

import java.util.List;

/**
 * <p>
 * 角色和部门关联表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDept> {

    List<SysRoleDept> query(SysRoleDeptSearch search);

    IPage<SysRoleDept> queryPage(IPage<SysRoleDept> page, SysRoleDeptSearch search);

}