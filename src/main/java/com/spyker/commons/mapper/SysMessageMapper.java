package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysMessage;
import com.spyker.commons.search.SysMessageSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 通知消息 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Mapper
public interface SysMessageMapper extends BaseMapper<SysMessage> {

    List<SysMessage> query(SysMessageSearch search);

    IPage<SysMessage> queryPage(IPage<SysMessage> page, SysMessageSearch search);
}