package com.spyker.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.base.entity.SysLogininfor;
import com.spyker.base.search.SysLogininforSearch;

import java.util.List;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {

    List<SysLogininfor> query(SysLogininforSearch search);

    IPage<SysLogininfor> queryPage(IPage<SysLogininfor> page, SysLogininforSearch search);

}