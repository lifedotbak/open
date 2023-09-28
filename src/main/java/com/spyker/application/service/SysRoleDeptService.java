package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.SysRoleDept;
import com.spyker.application.search.SysRoleDeptSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 角色和部门关联表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysRoleDeptService extends IService<SysRoleDept> {

    List<SysRoleDept> query(SysRoleDeptSearch search);

    IPage<SysRoleDept> queryPage(IPage<SysRoleDept> page, SysRoleDeptSearch search);

    SysRoleDept get(String id);

    RestResponse<?> insert(SysRoleDept SysRoleDept);

    RestResponse<?> update(SysRoleDept SysRoleDept);

    RestResponse<?> delete(String id);

}