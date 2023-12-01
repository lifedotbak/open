package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.RankInfo;
import com.spyker.commons.mapper.RankInfoMapper;
import com.spyker.commons.search.RankInfoSearch;
import com.spyker.commons.service.RankInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * Rank表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RankInfoServiceImpl extends ServiceImpl<RankInfoMapper, RankInfo> implements RankInfoService {

    private final RankInfoMapper rankInfoMapper;

    @Override
    public List<RankInfo> query(RankInfoSearch search) {
        List<RankInfo> result = rankInfoMapper.query(search);

        return result;
    }

    @Override
    public IPage<RankInfo> queryPage(IPage<RankInfo> page, RankInfoSearch search) {
        page = rankInfoMapper.queryPage(page, search);

        return page;
    }

    @Override
    public RankInfo get(String id) {
        RankInfo result = getById(id);
        return result;
    }

    @Override
    public boolean insert(RankInfo RankInfo) {
        return save(RankInfo);
    }

    @Override
    public boolean update(RankInfo RankInfo) {
        return updateById(RankInfo);
    }

    @Override
    public boolean delete(String id) {
        return removeById(id);
    }

}