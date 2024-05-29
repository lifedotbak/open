package com.spyker.framework.tencent.pay.v3.entity.refunds;

import lombok.Data;

@Data
public class RefundsAmount {

    /** int 是 退款金额，单位为分，只能为整数，不能超过原订单支付金额。 */
    private int refund;

    /** 原订单金额 是 原支付交易的订单总金额，单位为分，只能为整数。 */
    private int total;

    /** 退款币种 */
    private String currency = "CNY";
}