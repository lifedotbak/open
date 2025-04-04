package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysMessage;
import com.spyker.commons.search.SysMessageSearch;

import java.util.List;

/**
 * 通知消息 服务接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
public interface SysMessageService extends IService<SysMessage> {

    List<SysMessage> query(SysMessageSearch search);

    IPage<SysMessage> queryPage(IPage<SysMessage> page, SysMessageSearch search);

    SysMessage get(String id);

    SysMessage insert(SysMessage sysMessage);

    SysMessage update(SysMessage sysMessage);

    boolean delete(String id);
}
