package com.spyker.commons.mapper;

import com.spyker.commons.entity.OpenUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.OpenUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户表 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenUserMapperTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenUserMapper openUserMapper;
}