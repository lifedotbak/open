package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysDept;
import com.spyker.commons.search.SysDeptSearch;

import java.util.List;

/**
 * 部门表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> query(SysDeptSearch search);

    IPage<SysDept> queryPage(IPage<SysDept> page, SysDeptSearch search);

    SysDept get(String id);

    SysDept insert(SysDept SysDept);

    SysDept update(SysDept SysDept);

    void delete(String id);
}