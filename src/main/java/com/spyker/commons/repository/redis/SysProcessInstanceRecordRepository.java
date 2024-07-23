package com.spyker.commons.repository.redis;

import com.spyker.commons.entity.SysProcessInstanceRecord;

import org.springframework.data.repository.CrudRepository;

/**
 * 流程记录
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
/** 一个负责存储和检索的组件，定义一个repository接口,和mapper功能类似，但不能放到mapper包下面，因为mybatis的扫描，放同一包下面会提示类冲突 */
public interface SysProcessInstanceRecordRepository
        extends CrudRepository<SysProcessInstanceRecord, String> {}