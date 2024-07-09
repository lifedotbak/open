package cc.flyflow.biz.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cc.flyflow.biz.entity.Message;
import cc.flyflow.biz.mapper.MessageMapper;
import cc.flyflow.biz.service.IClearService;
import cc.flyflow.biz.service.IMessageService;
import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.third.MessageDto;
import cc.flyflow.common.utils.TenantUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通知消息 服务实现类
 *
 * @author xiaoge
 * @since 2023-07-25
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements IMessageService, IClearService {

    /**
     * 查询未读数量
     *
     * @return
     */
    @Override
    public R queryUnreadNum(Long lastId) {
        if (lastId == null) {
            lastId = 0L;
        }
        String userId = StpUtil.getLoginIdAsString();
        Long num =
                this.lambdaQuery()
                        .eq(Message::getReaded, false)
                        .eq(Message::getTenantId, TenantUtil.get())
                        .eq(Message::getUserId, userId)
                        .count();

        Message message =
                this.lambdaQuery()
                        .eq(Message::getTenantId, TenantUtil.get())
                        .gt(Message::getId, lastId)
                        .eq(Message::getUserId, userId)
                        .orderByDesc(Message::getCreateTime)
                        .last("limit 1")
                        .one();

        Dict set =
                Dict.create()
                        .set("num", num)
                        .set("maxId", message == null ? null : message.getId())
                        .set("title", message == null ? null : message.getTitle())
                        .set("content", message == null ? null : message.getContent());

        return R.success(set);
    }

    /**
     * 保存消息
     *
     * @param messageDto
     * @return
     */
    @Override
    public R saveMessage(MessageDto messageDto) {
        Message message = BeanUtil.copyProperties(messageDto, Message.class);
        this.save(message);
        return R.success();
    }

    /**
     * 查询列表
     *
     * @param pageDto
     * @return
     */
    @Override
    public R<Page<Message>> queryList(cc.flyflow.common.dto.MessageDto pageDto) {
        Page<Message> messagePage =
                this.lambdaQuery()
                        .eq(Message::getUserId, StpUtil.getLoginIdAsString())
                        .eq(Message::getTenantId, TenantUtil.get())
                        .eq(pageDto.getReaded() != null, Message::getReaded, pageDto.getReaded())
                        .orderByDesc(Message::getCreateTime)
                        .page(new Page<>(pageDto.getPageNum(), pageDto.getPageSize()));
        return R.success(messagePage);
    }

    /**
     * 删除消息
     *
     * @param messageDto
     * @return
     */
    @Override
    public R delete(cc.flyflow.common.dto.MessageDto messageDto) {
        this.removeById(messageDto.getId());
        return R.success();
    }

    /**
     * 置为已读
     *
     * @param messageDto
     * @return
     */
    @Override
    public R read(cc.flyflow.common.dto.MessageDto messageDto) {
        String userId = StpUtil.getLoginIdAsString();
        this.lambdaUpdate()
                .set(Message::getReaded, true)
                .eq(Message::getUserId, userId)
                .eq(Message::getId, messageDto.getId())
                .eq(Message::getReaded, false)
                .eq(Message::getTenantId, TenantUtil.get())
                .update(new Message());
        return R.success();
    }

    /**
     * 全部已读
     *
     * @return
     */
    @Override
    public R readAll() {
        String userId = StpUtil.getLoginIdAsString();

        this.lambdaUpdate()
                .set(Message::getReaded, true)
                .eq(Message::getUserId, userId)
                .eq(Message::getReaded, false)
                .eq(Message::getTenantId, TenantUtil.get())
                .update(new Message());
        return R.success();
    }

    /**
     * 清理数据
     *
     * @param uniqueId 流程唯一id
     * @param flowIdList process表 流程id集合
     * @param processIdList process表的注解id集合
     * @param tenantId 租户id
     */
    @Override
    public void clearProcess(
            String uniqueId, List<String> flowIdList, List<Long> processIdList, String tenantId) {

        this.lambdaUpdate()
                .in(Message::getFlowId, flowIdList)
                .eq(Message::getTenantId, tenantId)
                .remove();
    }
}