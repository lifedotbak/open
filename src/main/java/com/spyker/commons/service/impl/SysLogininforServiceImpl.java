package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysLogininfor;
import com.spyker.commons.mapper.SysLogininforMapper;
import com.spyker.commons.search.SysLogininforSearch;
import com.spyker.commons.service.SysLogininforService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor>
        implements SysLogininforService {

    private final SysLogininforMapper sysLogininforMapper;

    @Override
    public List<SysLogininfor> query(SysLogininforSearch search) {
        List<SysLogininfor> SysLogininforList = sysLogininforMapper.query(search);

        return SysLogininforList;
    }

    @Override
    public IPage<SysLogininfor> queryPage(IPage<SysLogininfor> page, SysLogininforSearch search) {
        page = sysLogininforMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysLogininfor get(String id) {
        SysLogininfor SysLogininfor = getById(id);

        return SysLogininfor;
    }

    @Override
    public RestResponse<?> insert(SysLogininfor SysLogininfor) {
        save(SysLogininfor);

        return RestResponse.success(SysLogininfor);
    }

    @Override
    public RestResponse<?> update(SysLogininfor SysLogininfor) {
        updateById(SysLogininfor);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}