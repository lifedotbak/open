package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenDept;
import com.spyker.commons.search.OpenDeptSearch;

import java.util.List;

/**
 * 部门表 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenDeptService extends IService<OpenDept> {

    // @formatter:off

    List<OpenDept> query(OpenDeptSearch search);

    IPage<OpenDept> queryPage(IPage<OpenDept> page, OpenDeptSearch search);

    OpenDept get(String id);

    OpenDept insert(OpenDept openDept);

    OpenDept update(OpenDept openDept);

    boolean delete(String id);
}