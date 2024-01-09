package com.spyker.commons.mapper;

import com.spyker.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户信息表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Slf4j
public class SysUserMapperTest extends BaseTest {

    @Autowired private SysUserMapper sysUserMapper;

    @Test
    public void queryRolesById() {
        sysUserMapper.queryRolesById("1");
    }
}