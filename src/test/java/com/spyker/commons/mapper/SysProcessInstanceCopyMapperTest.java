package com.spyker.commons.mapper;

import com.spyker.BaseTest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

/** 流程抄送数据 Mapper 接口测试类 */
@Slf4j
public class SysProcessInstanceCopyMapperTest extends BaseTest {

    @Autowired private SysProcessInstanceCopyMapper sysProcessInstanceCopyMapper;
}
