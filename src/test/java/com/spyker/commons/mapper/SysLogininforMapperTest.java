package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysLogininfor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysLogininforMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统访问记录 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysLogininforMapperTest extends BaseTest {

    // @formatter:off

    @Autowired private SysLogininforMapper sysLogininforMapper;
}