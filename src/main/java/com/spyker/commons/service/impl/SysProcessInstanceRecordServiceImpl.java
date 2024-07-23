package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysProcessInstanceRecord;
import com.spyker.commons.mapper.SysProcessInstanceRecordMapper;
import com.spyker.commons.search.SysProcessInstanceRecordSearch;
import com.spyker.commons.service.SysProcessInstanceRecordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程记录 服务实现类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "SysProcessInstanceRecord")
public class SysProcessInstanceRecordServiceImpl
        extends ServiceImpl<SysProcessInstanceRecordMapper, SysProcessInstanceRecord>
        implements SysProcessInstanceRecordService {

    private final SysProcessInstanceRecordMapper sysProcessInstanceRecordMapper;

    @Override
    public List<SysProcessInstanceRecord> query(SysProcessInstanceRecordSearch search) {

        List<SysProcessInstanceRecord> result = sysProcessInstanceRecordMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<SysProcessInstanceRecord> queryPage(
            IPage<SysProcessInstanceRecord> page, SysProcessInstanceRecordSearch search) {

        page = sysProcessInstanceRecordMapper.queryPage(page, search);
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
    public SysProcessInstanceRecord get(String id) {
        SysProcessInstanceRecord result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    /**
     * @param sysProcessInstanceRecord
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#sysProcessInstanceRecord.id")
    public SysProcessInstanceRecord insert(SysProcessInstanceRecord sysProcessInstanceRecord) {
        save(sysProcessInstanceRecord);
        return sysProcessInstanceRecord;
    }

    /**
     * @param sysProcessInstanceRecord
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#sysProcessInstanceRecord.id")
    public SysProcessInstanceRecord update(SysProcessInstanceRecord sysProcessInstanceRecord) {
        updateById(sysProcessInstanceRecord);
        return sysProcessInstanceRecord;
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