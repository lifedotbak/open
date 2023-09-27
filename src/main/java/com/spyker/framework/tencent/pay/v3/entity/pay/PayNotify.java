package com.spyker.framework.tencent.pay.v3.entity.pay;

import lombok.Data;

/**
 * 接收微信支付通知
 *
 * @author zhangzhaofeng
 */
@Data
public class PayNotify {

    /**
     * 通知的唯一ID
     */
    private String id;

    /**
     * 通知创建时间
     */
    private String create_time;

    /**
     * 通知类型 支付成功通知的类型为TRANSACTION.SUCCESS
     */
    private String event_type;

    /**
     * 通知数据类型 支付成功通知为encrypt-resource
     */
    private String resource_type;

    /**
     * 通知资源数据
     */
    private PayNotifyResource resource;

    /**
     * 回调摘要
     */
    private String summary;

}