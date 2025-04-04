package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcess;
import com.spyker.commons.search.SysProcessSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Mapper
public interface SysProcessMapper extends BaseMapper<SysProcess> {

    List<SysProcess> query(SysProcessSearch search);

    IPage<SysProcess> queryPage(IPage<SysProcess> page, SysProcessSearch search);
}
