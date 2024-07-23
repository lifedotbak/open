package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 通知公告表 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysNoticeMapperTest extends BaseTest {

    @Autowired private SysNoticeMapper sysNoticeMapper;
}