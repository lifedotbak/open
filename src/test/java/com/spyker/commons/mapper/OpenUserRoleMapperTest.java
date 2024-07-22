package com.spyker.commons.mapper;

import com.spyker.commons.entity.OpenUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.OpenUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户-角色 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenUserRoleMapperTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenUserRoleMapper openUserRoleMapper;
}