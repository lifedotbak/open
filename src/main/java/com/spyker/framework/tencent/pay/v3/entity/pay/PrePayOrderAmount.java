package com.spyker.framework.tencent.pay.v3.entity.pay;

import lombok.Data;

@Data
public class PrePayOrderAmount {

    /**
     * 订单总金额，单位为分。
     */
    private int total;

    /**
     * CNY：人民币，境内商户号仅支持人民币。
     */
    private String currency = "CNY";
}