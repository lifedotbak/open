package com.spyker.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.base.entity.SysDictType;
import com.spyker.base.mapper.SysDictTypeMapper;
import com.spyker.base.search.SysDictTypeSearch;
import com.spyker.base.service.SysDictTypeService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    private final SysDictTypeMapper sysDictTypeMapper;

    @Override
    public List<SysDictType> query(SysDictTypeSearch search) {
        List<SysDictType> SysDictTypeList = sysDictTypeMapper.query(search);

        return SysDictTypeList;
    }

    @Override
    public IPage<SysDictType> queryPage(IPage<SysDictType> page, SysDictTypeSearch search) {
        page = sysDictTypeMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysDictType get(String id) {
        SysDictType SysDictType = getById(id);

        return SysDictType;
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