package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysProcessStarter;
import com.spyker.commons.mapper.SysProcessStarterMapper;
import com.spyker.commons.search.SysProcessStarterSearch;
import com.spyker.commons.service.SysProcessStarterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程发起人范围 服务实现类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "SysProcessStarter")
public class SysProcessStarterServiceImpl
        extends ServiceImpl<SysProcessStarterMapper, SysProcessStarter>
        implements SysProcessStarterService {

    private final SysProcessStarterMapper sysProcessStarterMapper;

    @Override
    public List<SysProcessStarter> query(SysProcessStarterSearch search) {

        List<SysProcessStarter> result = sysProcessStarterMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<SysProcessStarter> queryPage(
            IPage<SysProcessStarter> page, SysProcessStarterSearch search) {

        page = sysProcessStarterMapper.queryPage(page, search);
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
    public SysProcessStarter get(String id) {
        SysProcessStarter result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    /**
     * @param sysProcessStarter
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#sysProcessStarter.id")
    public SysProcessStarter insert(SysProcessStarter sysProcessStarter) {
        save(sysProcessStarter);
        return sysProcessStarter;
    }

    /**
     * @param sysProcessStarter
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#sysProcessStarter.id")
    public SysProcessStarter update(SysProcessStarter sysProcessStarter) {
        updateById(sysProcessStarter);
        return sysProcessStarter;
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