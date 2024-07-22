package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessInstanceCopy;
import com.spyker.commons.search.OpenProcessInstanceCopySearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程抄送数据 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessInstanceCopyMapper extends BaseMapper<OpenProcessInstanceCopy> {

    // @formatter:off

    List<OpenProcessInstanceCopy> query(OpenProcessInstanceCopySearch search);

    IPage<OpenProcessInstanceCopy> queryPage(
            IPage<OpenProcessInstanceCopy> page, OpenProcessInstanceCopySearch search);
}