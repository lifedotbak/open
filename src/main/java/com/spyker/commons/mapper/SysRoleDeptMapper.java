package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysRoleDept;
import com.spyker.commons.search.SysRoleDeptSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 角色和部门关联表 Mapper 接口 */
@Mapper
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDept> {

    List<SysRoleDept> query(SysRoleDeptSearch search);

    IPage<SysRoleDept> queryPage(IPage<SysRoleDept> page, SysRoleDeptSearch search);
}
