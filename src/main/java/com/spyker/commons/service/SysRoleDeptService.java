package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysRoleDept;
import com.spyker.commons.search.SysRoleDeptSearch;

import java.util.List;

/**
 * 角色和部门关联表 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface SysRoleDeptService extends IService<SysRoleDept> {

    List<SysRoleDept> query(SysRoleDeptSearch search);

    IPage<SysRoleDept> queryPage(IPage<SysRoleDept> page, SysRoleDeptSearch search);

    SysRoleDept get(String id);

    SysRoleDept insert(SysRoleDept sysRoleDept);

    SysRoleDept update(SysRoleDept sysRoleDept);

    boolean delete(String id);
}