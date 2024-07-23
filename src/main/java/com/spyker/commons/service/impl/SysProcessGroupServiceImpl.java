package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysProcessGroup;
import com.spyker.commons.mapper.SysProcessGroupMapper;
import com.spyker.commons.search.SysProcessGroupSearch;
import com.spyker.commons.service.SysProcessGroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务实现类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "SysProcessGroup")
public class SysProcessGroupServiceImpl extends ServiceImpl<SysProcessGroupMapper, SysProcessGroup>
        implements SysProcessGroupService {

    private final SysProcessGroupMapper sysProcessGroupMapper;

    @Override
    public List<SysProcessGroup> query(SysProcessGroupSearch search) {

        List<SysProcessGroup> result = sysProcessGroupMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<SysProcessGroup> queryPage(
            IPage<SysProcessGroup> page, SysProcessGroupSearch search) {

        page = sysProcessGroupMapper.queryPage(page, search);
        log.info("page------>{}", page);

        return page;
    }

    /**
     * @param id
     * @return @Cacheable @Cacheble注解表示这个方法有了缓存的功能，方法的返回值会被缓存下来，下一次调用该方法前， 会去检查是否缓存中已经有值
     *     ，如果有就直接返回，不调用方法。如果没有，就调用方法，然后把结果缓存起来。这个注解一般用在查询方法上。
     */
    @Override
    // @Cacheable(key = "#id")
    public SysProcessGroup get(String id) {
        SysProcessGroup result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    /**
     * @param sysProcessGroup
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#sysProcessGroup.id")
    public SysProcessGroup insert(SysProcessGroup sysProcessGroup) {
        save(sysProcessGroup);
        return sysProcessGroup;
    }

    /**
     * @param sysProcessGroup
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#sysProcessGroup.id")
    public SysProcessGroup update(SysProcessGroup sysProcessGroup) {
        updateById(sysProcessGroup);
        return sysProcessGroup;
    }

    /**
     * @param id
     * @return @CacheEvict 使用了CacheEvict注解的方法，会清空指定缓存。 一般用在更新或者删除的方法上。
     */
    @Override
    // @CacheEvict(key = "#id")
    public boolean delete(String id) {
        return removeById(id);
    }
}