package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysOperLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysOperLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 操作日志记录 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysOperLogMapperTest extends BaseTest {

    // @formatter:off

    @Autowired private SysOperLogMapper sysOperLogMapper;
}