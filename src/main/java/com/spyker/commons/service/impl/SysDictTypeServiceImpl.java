package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysDictType;
import com.spyker.commons.mapper.SysDictTypeMapper;
import com.spyker.commons.search.SysDictTypeSearch;
import com.spyker.commons.service.SysDictTypeService;
import com.spyker.framework.web.response.RestResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 字典类型表 服务实现类 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType>
        implements SysDictTypeService {

    private final SysDictTypeMapper sysDictTypeMapper;

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

    @Override
    public SysDictType get(String id) {
        SysDictType result = getById(id);

        return result;
    }

    @Override
    public RestResponse<?> insert(SysDictType sysDictType) {
        save(sysDictType);

        return RestResponse.success(sysDictType);
    }

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
    public RestResponse<?> update(SysDictType sysDictType) {
        updateById(sysDictType);

        return RestResponse.success();
    }
}
