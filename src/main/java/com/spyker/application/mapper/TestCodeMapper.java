package com.spyker.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.application.entity.TestCode;
import com.spyker.application.search.TestCodeSearch;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-14
 */
public interface TestCodeMapper extends BaseMapper<TestCode> {

    List<TestCode> query(TestCodeSearch search);

    IPage<TestCode> queryPage(IPage<TestCode> page, TestCodeSearch search);

}