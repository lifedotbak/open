package com.spyker.framework.tencent.pay.v3.entity.pay;

import lombok.Data;

/** 微信通知数据解密后对象 */
@Data
public class PayNotifyCiphertextParse {

    /** 公众号ID */
    private String appid;

    /** 直连商户号 */
    private String mchid;

    /** 商户订单号 */
    private String out_trade_no;

    /** 微信支付订单号 */
    private String transaction_id;

    /** 交易类型 */
    private String trade_type;

    /** 交易状态 */
    private String trade_state;

    /** 交易状态描述 */
    private String trade_state_desc;

    /** 付款银行 */
    private String bank_type;

    /** 支付完成时间 */
    private String success_time;

    /** 附加数据 */
    private String attach;

    /** 支付者 */
    private Payer payer;

    /** 订单金额 */
    private PayNotifyCiphertextParseAmount amount;
}
