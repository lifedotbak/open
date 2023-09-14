package com.spyker.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.application.entity.TestCode;
import com.spyker.application.mapper.TestCodeMapper;
import com.spyker.application.search.TestCodeSearch;
import com.spyker.application.service.TestCodeService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-14
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TestCodeServiceImpl extends ServiceImpl<TestCodeMapper, TestCode> implements TestCodeService {

    private final TestCodeMapper testCodeMapper;

    @Override
    public List<TestCode> query(TestCodeSearch search) {
        List<TestCode> TestCodeList = testCodeMapper.query(search);

        return TestCodeList;
    }

    @Override
    public IPage<TestCode> queryPage(IPage<TestCode> page, TestCodeSearch search) {
        page = testCodeMapper.queryPage(page, search);

        return page;
    }

    @Override
    public TestCode get(String id) {
        TestCode TestCode = getById(id);

        return TestCode;
    }

    @Override
    public RestResponse<?> insert(TestCode TestCode) {
        save(TestCode);

        return RestResponse.success(TestCode);
    }

    @Override
    public RestResponse<?> update(TestCode TestCode) {
        updateById(TestCode);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}