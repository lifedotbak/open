package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessForm;
import com.spyker.commons.search.OpenProcessFormSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程表单 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessFormMapper extends BaseMapper<OpenProcessForm> {

    // @formatter:off

    List<OpenProcessForm> query(OpenProcessFormSearch search);

    IPage<OpenProcessForm> queryPage(IPage<OpenProcessForm> page, OpenProcessFormSearch search);
}