package com.spyker.commons.redisrepository;

import com.spyker.commons.entity.Person;

import org.springframework.data.repository.CrudRepository;

/** 一个负责存储和检索的组件，定义一个repository接口,和mapper功能类似，但不能放到mapper包下面，因为mybatis的扫描，放同一包下面会提示类冲突 */
public interface PersonTestRepository extends CrudRepository<Person, String> {}