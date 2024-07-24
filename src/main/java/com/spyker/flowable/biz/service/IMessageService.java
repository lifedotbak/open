package com.spyker.flowable.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.flowable.biz.entity.Message;
import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.dto.third.MessageDto;

/**
 * 通知消息 服务类
 *
 * @author xiaoge
 * @since 2023-07-25
 */
public interface IMessageService extends IService<Message> {
    /**
     * 查询未读数量
     *
     * @return
     */
    R queryUnreadNum(Long lastId);

    /**
     * 保存消息
     *
     * @param messageDto
     * @return
     */
    R saveMessage(MessageDto messageDto);

    /**
     * 查询列表
     *
     * @param pageDto
     * @return
     */
    R<Page<Message>> queryList(com.spyker.flowable.common.dto.MessageDto pageDto);

    /**
     * 删除消息
     *
     * @param messageDto
     * @return
     */
    R delete(com.spyker.flowable.common.dto.MessageDto messageDto);

    /**
     * 置为已读
     *
     * @param messageDto
     * @return
     */
    R read(com.spyker.flowable.common.dto.MessageDto messageDto);

    /**
     * 全部已读
     *
     * @return
     */
    R readAll();
}