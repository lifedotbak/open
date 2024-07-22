package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenDeptUser;
import com.spyker.commons.search.OpenDeptUserSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门-主管表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenDeptUserMapper extends BaseMapper<OpenDeptUser> {

    // @formatter:off

    List<OpenDeptUser> query(OpenDeptUserSearch search);

    IPage<OpenDeptUser> queryPage(IPage<OpenDeptUser> page, OpenDeptUserSearch search);
}