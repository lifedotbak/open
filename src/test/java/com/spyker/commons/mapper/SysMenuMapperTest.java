package com.spyker.commons.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spyker.BaseTest;

import lombok.extern.slf4j.Slf4j;

/**
 * 菜单权限表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Slf4j
public class SysMenuMapperTest extends BaseTest {

    @Autowired private SysMenuMapper sysMenuMapper;

    @Test
    public void get() {
        sysMenuMapper.selectById("1");
    }
}