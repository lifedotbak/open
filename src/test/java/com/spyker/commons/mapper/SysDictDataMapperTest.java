package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysDictData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 字典数据表 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysDictDataMapperTest extends BaseTest {

    @Autowired private SysDictDataMapper sysDictDataMapper;
}