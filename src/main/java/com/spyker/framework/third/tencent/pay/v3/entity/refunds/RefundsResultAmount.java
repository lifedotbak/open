package com.spyker.framework.third.tencent.pay.v3.entity.refunds;

import lombok.Data;

@Data
public class RefundsResultAmount {
    /**
     * 订单金额 total int 是 订单总金额，单位为分
     */
    private int total;

    /**
     * 退款金额 refund int 是 退款标价金额，单位为分，可以做部分退款
     */
    private int refund;

    /**
     * 用户支付金额 payer_total int 是 现金支付金额，单位为分，只能为整数
     */
    private int payer_total;

    /**
     * 用户退款金额 payer_refund int 是 退款给用户的金额，不包含所有优惠券金额
     */
    private int payer_refund;

    /**
     * 应结退款金额 settlement_refund int 是
     * 去掉非充值代金券退款金额后的退款金额，单位为分，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    private int settlement_refund;

    /**
     * 应结订单金额 settlement_total int 是 应结订单金额=订单金额-免充值代金券金额，应结订单金额<=订单金额，单位为分
     */
    private int settlement_total;

    /**
     * 优惠退款金额 discount_refund int 是
     * 优惠退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠，单位为分
     */
    private int discount_refund;

    /**
     * 退款币种 currency string[1, 16] 是 符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。
     */
    private String currency;
}