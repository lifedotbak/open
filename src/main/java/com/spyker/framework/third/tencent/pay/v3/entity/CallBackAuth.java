package com.spyker.framework.third.tencent.pay.v3.entity;

import lombok.Data;

/**
 * APP调起支付API,生成sign对象
 *
 * @author zhangzhaofeng
 */
@Data
public class CallBackAuth {

    private String appId;
    private String timeStamp;
    private String nonceStr;
    private String prepayid;
    private String packageValue;
}