package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.OpenProcessGroup;
import com.spyker.commons.mapper.OpenProcessGroupMapper;
import com.spyker.commons.search.OpenProcessGroupSearch;
import com.spyker.commons.service.OpenProcessGroupService;

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
 * @since 2024-07-22
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "OpenProcessGroup")
public class OpenProcessGroupServiceImpl
        extends ServiceImpl<OpenProcessGroupMapper, OpenProcessGroup>
        implements OpenProcessGroupService {

    // @formatter:off

    private final OpenProcessGroupMapper openProcessGroupMapper;

    @Override
    public List<OpenProcessGroup> query(OpenProcessGroupSearch search) {

        List<OpenProcessGroup> result = openProcessGroupMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<OpenProcessGroup> queryPage(
            IPage<OpenProcessGroup> page, OpenProcessGroupSearch search) {

        page = openProcessGroupMapper.queryPage(page, search);
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
    public OpenProcessGroup get(String id) {
        OpenProcessGroup result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    /**
     * @param openProcessGroup
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#openProcessGroup.id")
    public OpenProcessGroup insert(OpenProcessGroup openProcessGroup) {
        save(openProcessGroup);
        return openProcessGroup;
    }

    /**
     * @param openProcessGroup
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#openProcessGroup.id")
    public OpenProcessGroup update(OpenProcessGroup openProcessGroup) {
        updateById(openProcessGroup);
        return openProcessGroup;
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