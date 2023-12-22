package com.spyker.commons.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysPost;
import com.spyker.commons.search.SysPostSearch;

/**
 * <p>
 * 岗位信息表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {

	List<SysPost> query(SysPostSearch search);

	IPage<SysPost> queryPage(IPage<SysPost> page, SysPostSearch search);

}