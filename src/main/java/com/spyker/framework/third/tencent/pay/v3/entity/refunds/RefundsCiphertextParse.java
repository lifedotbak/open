package com.spyker.framework.third.tencent.pay.v3.entity.refunds;

import lombok.Data;

/**
 * 回调resource对象进行解密后，得到的资源对象
 *
 * @author spyker
 */
@Data
public class RefundsCiphertextParse {

    private String mchid;
    private String transaction_id;
    private String out_trade_no;
    private String refund_id;
    private String out_refund_no;
    private String refund_status;
    private String success_time;
    private String user_received_account;

    private RefundsCiphertextParseAmount amount;
}