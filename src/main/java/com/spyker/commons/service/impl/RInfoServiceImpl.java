package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.RInfo;
import com.spyker.commons.mapper.RInfoMapper;
import com.spyker.commons.search.RInfoSearch;
import com.spyker.commons.service.RInfoService;
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
 * @since 2023-11-24
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RInfoServiceImpl extends ServiceImpl<RInfoMapper, RInfo> implements RInfoService {

    private final RInfoMapper rInfoMapper;

    @Override
    public List<RInfo> query(RInfoSearch search) {
        List<RInfo> result = rInfoMapper.query(search);

        return result;
    }

    @Override
    public IPage<RInfo> queryPage(IPage<RInfo> page, RInfoSearch search) {
        page = rInfoMapper.queryPage(page, search);

        return page;
    }

    @Override
    public RInfo get(String id) {
        RInfo result = getById(id);
        return result;
    }

    @Override
    public boolean insert(RInfo RInfo) {
        return save(RInfo);
    }

    @Override
    public boolean update(RInfo RInfo) {
        return updateById(RInfo);
    }

    @Override
    public boolean delete(String id) {
        return removeById(id);
    }

}