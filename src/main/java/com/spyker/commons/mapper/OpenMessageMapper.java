package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenMessage;
import com.spyker.commons.search.OpenMessageSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 通知消息 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenMessageMapper extends BaseMapper<OpenMessage> {

    // @formatter:off

    List<OpenMessage> query(OpenMessageSearch search);

    IPage<OpenMessage> queryPage(IPage<OpenMessage> page, OpenMessageSearch search);
}