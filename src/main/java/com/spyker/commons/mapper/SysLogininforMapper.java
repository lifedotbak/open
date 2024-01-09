package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysLogininfor;
import com.spyker.commons.search.SysLogininforSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统访问记录 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Mapper
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {

    List<SysLogininfor> query(SysLogininforSearch search);

    IPage<SysLogininfor> queryPage(IPage<SysLogininfor> page, SysLogininforSearch search);
}