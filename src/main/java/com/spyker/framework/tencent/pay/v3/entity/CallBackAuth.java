package com.spyker.framework.tencent.pay.v3.entity;

import lombok.Data;

/** APP调起支付API,生成sign对象 */
@Data
public class CallBackAuth {

    private String appId;
    private String timeStamp;
    private String nonceStr;
    private String prepayid;
    private String packageValue;
}
