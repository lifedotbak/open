package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessForm;
import com.spyker.commons.search.SysProcessFormSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程表单 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Mapper
public interface SysProcessFormMapper extends BaseMapper<SysProcessForm> {

    List<SysProcessForm> query(SysProcessFormSearch search);

    IPage<SysProcessForm> queryPage(IPage<SysProcessForm> page, SysProcessFormSearch search);
}