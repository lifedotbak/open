package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysDictType;
import com.spyker.commons.mapper.SysDictTypeMapper;
import com.spyker.commons.search.SysDictTypeSearch;
import com.spyker.commons.service.SysDictTypeService;
import com.spyker.framework.response.RestResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典类型表 服务实现类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType>
        implements SysDictTypeService {

    private final SysDictTypeMapper sysDictTypeMapper;

    @Override
    public List<SysDictType> query(SysDictTypeSearch search) {
        List<SysDictType> result = sysDictTypeMapper.query(search);

        return result;
    }

    @Override
    public IPage<SysDictType> queryPage(IPage<SysDictType> page, SysDictTypeSearch search) {
        page = sysDictTypeMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysDictType get(String id) {
        SysDictType result = getById(id);

        return result;
    }

    @Override
    public RestResponse<?> insert(SysDictType SysDictType) {
        save(SysDictType);

        return RestResponse.success(SysDictType);
    }

    @Override
    public RestResponse<?> update(SysDictType SysDictType) {
        updateById(SysDictType);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }
}