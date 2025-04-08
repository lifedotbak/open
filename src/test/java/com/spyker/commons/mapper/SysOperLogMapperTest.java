package com.spyker.commons.mapper;

import com.spyker.BaseTest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

/** 操作日志记录 Mapper 接口测试类 */
@Slf4j
public class SysOperLogMapperTest extends BaseTest {

    @Autowired private SysOperLogMapper sysOperLogMapper;
}
