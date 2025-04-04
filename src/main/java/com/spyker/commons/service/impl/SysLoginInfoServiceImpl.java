package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysLoginInfo;
import com.spyker.commons.mapper.SysLoginInfoMapper;
import com.spyker.commons.search.SysLoginInfoSearch;
import com.spyker.commons.service.SysLoginInfoService;
import com.spyker.framework.web.response.RestResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统访问记录 服务实现类
 *
 * @author 121232224@qq.com
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoMapper, SysLoginInfo>
        implements SysLoginInfoService {

    private final SysLoginInfoMapper sysLoginInfoMapper;

    @Override
    public List<SysLoginInfo> query(SysLoginInfoSearch search) {
        List<SysLoginInfo> sysLogininforList = sysLoginInfoMapper.query(search);

        return sysLogininforList;
    }

    @Override
    public IPage<SysLoginInfo> queryPage(IPage<SysLoginInfo> page, SysLoginInfoSearch search) {
        page = sysLoginInfoMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysLoginInfo get(String id) {
        SysLoginInfo sysLogininfor = getById(id);

        return sysLogininfor;
    }

    @Override
    public RestResponse<?> insert(SysLoginInfo sysLogininfor) {
        save(sysLogininfor);

        return RestResponse.success(sysLogininfor);
    }

    @Override
    public RestResponse<?> update(SysLoginInfo sysLogininfor) {
        updateById(sysLogininfor);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }
}
