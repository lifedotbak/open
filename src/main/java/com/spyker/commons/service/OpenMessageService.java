package com.spyker.commons.service;

import com.spyker.commons.entity.OpenMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.OpenMessageSearch;

/**
 * 通知消息 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenMessageService extends IService<OpenMessage> {

    // @formatter:off

    List<OpenMessage> query(OpenMessageSearch search);

    IPage<OpenMessage> queryPage(IPage<OpenMessage> page, OpenMessageSearch search);

    OpenMessage get(String id);

    OpenMessage insert(OpenMessage openMessage);

    OpenMessage update(OpenMessage openMessage);

    boolean delete(String id);
}