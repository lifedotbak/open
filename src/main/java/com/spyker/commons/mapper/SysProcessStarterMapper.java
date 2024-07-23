package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessStarter;
import com.spyker.commons.search.SysProcessStarterSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程发起人范围 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Mapper
public interface SysProcessStarterMapper extends BaseMapper<SysProcessStarter> {

    List<SysProcessStarter> query(SysProcessStarterSearch search);

    IPage<SysProcessStarter> queryPage(
            IPage<SysProcessStarter> page, SysProcessStarterSearch search);
}