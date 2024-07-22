package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.OpenProcessInstanceExecution;
import com.spyker.commons.mapper.OpenProcessInstanceExecutionMapper;
import com.spyker.commons.search.OpenProcessInstanceExecutionSearch;
import com.spyker.commons.service.OpenProcessInstanceExecutionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程执行id数据 服务实现类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "OpenProcessInstanceExecution")
public class OpenProcessInstanceExecutionServiceImpl
        extends ServiceImpl<OpenProcessInstanceExecutionMapper, OpenProcessInstanceExecution>
        implements OpenProcessInstanceExecutionService {

    // @formatter:off

    private final OpenProcessInstanceExecutionMapper openProcessInstanceExecutionMapper;

    @Override
    public List<OpenProcessInstanceExecution> query(OpenProcessInstanceExecutionSearch search) {

        List<OpenProcessInstanceExecution> result =
                openProcessInstanceExecutionMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<OpenProcessInstanceExecution> queryPage(
            IPage<OpenProcessInstanceExecution> page, OpenProcessInstanceExecutionSearch search) {

        page = openProcessInstanceExecutionMapper.queryPage(page, search);
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
    public OpenProcessInstanceExecution get(String id) {
        OpenProcessInstanceExecution result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    /**
     * @param openProcessInstanceExecution
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#openProcessInstanceExecution.id")
    public OpenProcessInstanceExecution insert(
            OpenProcessInstanceExecution openProcessInstanceExecution) {
        save(openProcessInstanceExecution);
        return openProcessInstanceExecution;
    }

    /**
     * @param openProcessInstanceExecution
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#openProcessInstanceExecution.id")
    public OpenProcessInstanceExecution update(
            OpenProcessInstanceExecution openProcessInstanceExecution) {
        updateById(openProcessInstanceExecution);
        return openProcessInstanceExecution;
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