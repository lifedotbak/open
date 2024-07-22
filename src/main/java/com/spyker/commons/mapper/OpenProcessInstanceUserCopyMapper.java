package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessInstanceUserCopy;
import com.spyker.commons.search.OpenProcessInstanceUserCopySearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程抄送数据--用户和实例唯一值 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessInstanceUserCopyMapper extends BaseMapper<OpenProcessInstanceUserCopy> {

    // @formatter:off

    List<OpenProcessInstanceUserCopy> query(OpenProcessInstanceUserCopySearch search);

    IPage<OpenProcessInstanceUserCopy> queryPage(
            IPage<OpenProcessInstanceUserCopy> page, OpenProcessInstanceUserCopySearch search);
}