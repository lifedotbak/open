package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysDept;
import com.spyker.commons.search.SysDeptSearch;

import java.util.List;

/**
 * 部门表 服务接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> query(SysDeptSearch search);

    IPage<SysDept> queryPage(IPage<SysDept> page, SysDeptSearch search);

    SysDept get(String id);

    SysDept insert(SysDept sysDept);

    SysDept update(SysDept sysDept);

    boolean delete(String id);
}
