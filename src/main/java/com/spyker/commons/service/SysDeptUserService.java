package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysDeptUser;
import com.spyker.commons.search.SysDeptUserSearch;

import java.util.List;

/** 部门-主管表 服务接口 */
public interface SysDeptUserService extends IService<SysDeptUser> {

    boolean delete(String id);

    SysDeptUser get(String id);

    SysDeptUser insert(SysDeptUser sysDeptUser);

    List<SysDeptUser> query(SysDeptUserSearch search);

    IPage<SysDeptUser> queryPage(IPage<SysDeptUser> page, SysDeptUserSearch search);

    SysDeptUser update(SysDeptUser sysDeptUser);
}
