package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysDictData;
import com.spyker.commons.mapper.SysDictDataMapper;
import com.spyker.commons.search.SysDictDataSearch;
import com.spyker.commons.service.SysDictDataService;

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
        List<SysDictData> result = sysDictDataMapper.query(search);

        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<SysDictData> queryPage(IPage<SysDictData> page, SysDictDataSearch search) {
        page = sysDictDataMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysDictData get(String id) {
        SysDictData result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    @Override
    public SysDictData insert(SysDictData sysDictData) {

        save(sysDictData);

        return sysDictData;
    }

    @Override
    public SysDictData update(SysDictData sysDictData) {

        updateById(sysDictData);

        return sysDictData;
    }

    @Override
    public void delete(String id) {
        removeById(id);
    }
}