package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.TestCode;
import com.spyker.application.search.TestCodeSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-14
 */
public interface TestCodeService extends IService<TestCode> {

    List<TestCode> query(TestCodeSearch search);

    IPage<TestCode> queryPage(IPage<TestCode> page, TestCodeSearch search);

    TestCode get(String id);

    RestResponse<?> insert(TestCode TestCode);

    RestResponse<?> update(TestCode TestCode);

    RestResponse<?> delete(String id);

}