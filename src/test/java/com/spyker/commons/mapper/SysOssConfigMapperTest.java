package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysOssConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysOssConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象存储配置表 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysOssConfigMapperTest extends BaseTest {

    @Autowired private SysOssConfigMapper sysOssConfigMapper;
}