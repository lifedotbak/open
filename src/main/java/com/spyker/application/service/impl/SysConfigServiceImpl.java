package com.spyker.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.application.entity.SysConfig;
import com.spyker.application.mapper.SysConfigMapper;
import com.spyker.application.search.SysConfigSearch;
import com.spyker.application.service.SysConfigService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    private final SysConfigMapper sysConfigMapper;

    @Override
    public boolean selectCaptchaEnabled() {
        return false;
    }

    @Override
    public List<SysConfig> query(SysConfigSearch search) {
        List<SysConfig> SysConfigList = sysConfigMapper.query(search);

        return SysConfigList;
    }

    @Override
    public IPage<SysConfig> queryPage(IPage<SysConfig> page, SysConfigSearch search) {
        page = sysConfigMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysConfig get(String id) {
        SysConfig SysConfig = getById(id);

        return SysConfig;
    }

    @Override
    public RestResponse<?> insert(SysConfig SysConfig) {
        save(SysConfig);

        return RestResponse.success(SysConfig);
    }

    @Override
    public RestResponse<?> update(SysConfig SysConfig) {
        updateById(SysConfig);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}