package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysOssConfig;
import com.spyker.commons.mapper.SysOssConfigMapper;
import com.spyker.commons.search.SysOssConfigSearch;
import com.spyker.commons.service.SysOssConfigService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 对象存储配置表 服务实现类 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysOssConfigServiceImpl extends ServiceImpl<SysOssConfigMapper, SysOssConfig>
        implements SysOssConfigService {

    private final SysOssConfigMapper sysOssConfigMapper;

    @Override
    public boolean delete(String id) {
        return removeById(id);
    }

    @Override
    public SysOssConfig get(String id) {
        SysOssConfig result = getById(id);

        log.info("result--->{}", result);

        return result;
    }

    @Override
    public boolean insert(SysOssConfig sysOssConfig) {
        return save(sysOssConfig);
    }

    @Override
    public List<SysOssConfig> query(SysOssConfigSearch search) {
        List<SysOssConfig> result = sysOssConfigMapper.query(search);

        return result;
    }

    @Override
    public IPage<SysOssConfig> queryPage(IPage<SysOssConfig> page, SysOssConfigSearch search) {
        page = sysOssConfigMapper.queryPage(page, search);

        return page;
    }

    @Override
    public boolean update(SysOssConfig sysOssConfig) {
        return updateById(sysOssConfig);
    }
}
