package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysMessage;
import com.spyker.commons.search.SysMessageSearch;

import java.util.List;

/** 通知消息 服务接口 */
public interface SysMessageService extends IService<SysMessage> {

    boolean delete(String id);

    SysMessage get(String id);

    SysMessage insert(SysMessage sysMessage);

    List<SysMessage> query(SysMessageSearch search);

    IPage<SysMessage> queryPage(IPage<SysMessage> page, SysMessageSearch search);

    SysMessage update(SysMessage sysMessage);
}
