package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysNotice;
import com.spyker.commons.search.SysNoticeSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 通知公告表 Mapper 接口 */
@Mapper
public interface SysNoticeMapper extends BaseMapper<SysNotice> {

    List<SysNotice> query(SysNoticeSearch search);

    IPage<SysNotice> queryPage(IPage<SysNotice> page, SysNoticeSearch search);
}
