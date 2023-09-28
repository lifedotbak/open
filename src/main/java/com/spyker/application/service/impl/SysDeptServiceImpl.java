package com.spyker.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.application.entity.SysDept;
import com.spyker.application.mapper.SysDeptMapper;
import com.spyker.application.search.SysDeptSearch;
import com.spyker.application.service.SysDeptService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    private final SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> query(SysDeptSearch search) {
        List<SysDept> SysDeptList = sysDeptMapper.query(search);

        return SysDeptList;
    }

    @Override
    public IPage<SysDept> queryPage(IPage<SysDept> page, SysDeptSearch search) {
        page = sysDeptMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysDept get(String id) {
        SysDept SysDept = getById(id);

        return SysDept;
    }

    @Override
    public RestResponse<?> insert(SysDept SysDept) {
        save(SysDept);

        return RestResponse.success(SysDept);
    }

    @Override
    public RestResponse<?> update(SysDept SysDept) {
        updateById(SysDept);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}