package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysDept;
import com.spyker.commons.search.SysDeptSearch;

import java.util.List;

/** 部门表 服务接口 */
public interface SysDeptService extends IService<SysDept> {

    boolean delete(String id);

    SysDept get(String id);

    SysDept insert(SysDept sysDept);

    List<SysDept> query(SysDeptSearch search);

    IPage<SysDept> queryPage(IPage<SysDept> page, SysDeptSearch search);

    SysDept update(SysDept sysDept);
}
