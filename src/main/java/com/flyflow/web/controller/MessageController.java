package com.flyflow.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyflow.biz.entity.Message;
import com.flyflow.biz.service.IMessageService;
import com.flyflow.common.config.NotWriteLogAnno;
import com.flyflow.common.dto.MessageDto;
import com.flyflow.common.dto.R;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.SneakyThrows;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/** 消息控制器 */
@Tag(name = "消息控制器", description = "消息控制器")
@RestController
@RequestMapping(value = {"message"})
public class MessageController {

    @Resource private IMessageService messageService;

    /** 获取未读数量 */
    @Operation(summary = "获取未读数量", description = "获取未读数量")
    @NotWriteLogAnno(printResultLog = false)
    @SneakyThrows
    @GetMapping("/unreadNum")
    public R queryUnreadNum(Long lastId) {
        return messageService.queryUnreadNum(lastId);
    }

    /**
     * 查询列表
     *
     * @param pageDto
     * @return
     */
    @Operation(summary = "查询列表", description = "查询列表")
    @PostMapping("queryList")
    public R<Page<Message>> queryList(@RequestBody MessageDto pageDto) {
        return messageService.queryList(pageDto);
    }

    /**
     * 删除消息
     *
     * @param messageDto
     * @return
     */
    @Operation(summary = "删除消息", description = "删除消息")
    @DeleteMapping("delete")
    public R delete(@RequestBody MessageDto messageDto) {
        return messageService.delete(messageDto);
    }

    /**
     * 置为已读
     *
     * @param messageDto
     * @return
     */
    @Operation(summary = "置为已读", description = "置为已读")
    @PostMapping("read")
    public R read(@RequestBody MessageDto messageDto) {
        return messageService.read(messageDto);
    }

    /**
     * 全部已读
     *
     * @return
     */
    @Operation(summary = "全部已读", description = "全部已读")
    @PostMapping("readAll")
    public R readAll() {
        return messageService.readAll();
    }
}