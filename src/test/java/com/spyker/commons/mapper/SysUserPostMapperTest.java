package com.spyker.commons.mapper;

import com.spyker.BaseTest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

/** 用户与岗位关联表 Mapper 接口测试类 */
@Slf4j
public class SysUserPostMapperTest extends BaseTest {

    @Autowired private SysUserPostMapper sysUserPostMapper;
}
