package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysDictData;
import com.spyker.commons.mapper.SysDictDataMapper;
import com.spyker.commons.search.SysDictDataSearch;
import com.spyker.commons.service.SysDictDataService;
import com.spyker.framework.response.RestResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典数据表 服务实现类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData>
        implements SysDictDataService {

    private final SysDictDataMapper sysDictDataMapper;

    @Override
    public List<SysDictData> query(SysDictDataSearch search) {
        List<SysDictData> SysDictDataList = sysDictDataMapper.query(search);

        return SysDictDataList;
    }

    @Override
    public IPage<SysDictData> queryPage(IPage<SysDictData> page, SysDictDataSearch search) {
        page = sysDictDataMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysDictData get(String id) {
        SysDictData SysDictData = getById(id);

        return SysDictData;
    }

    @Override
    public RestResponse<?> insert(SysDictData SysDictData) {
        save(SysDictData);

        return RestResponse.success(SysDictData);
    }

    @Override
    public RestResponse<?> update(SysDictData SysDictData) {
        updateById(SysDictData);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }
}