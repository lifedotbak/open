package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysDeptUser;
import com.spyker.commons.search.SysDeptUserSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 部门-主管表 Mapper 接口 */
@Mapper
public interface SysDeptUserMapper extends BaseMapper<SysDeptUser> {

    List<SysDeptUser> query(SysDeptUserSearch search);

    IPage<SysDeptUser> queryPage(IPage<SysDeptUser> page, SysDeptUserSearch search);
}
