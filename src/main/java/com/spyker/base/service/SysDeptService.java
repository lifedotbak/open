package com.spyker.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.base.entity.SysDept;
import com.spyker.base.search.SysDeptSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> query(SysDeptSearch search);

    IPage<SysDept> queryPage(IPage<SysDept> page, SysDeptSearch search);

    SysDept get(String id);

    RestResponse<?> insert(SysDept SysDept);

    RestResponse<?> update(SysDept SysDept);

    RestResponse<?> delete(String id);

}