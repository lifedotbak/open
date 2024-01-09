package com.spyker.framework.third.tencent.pay.v3.entity.refunds;

import lombok.Data;

/**
 * 回调resource对象进行解密后，得到的资源对象,账户信息
 *
 * @author spyker
 */
@Data
public class RefundsCiphertextParseAmount {

    private int total;
    private int refund;
    private int payer_total;
    private int payer_refund;
}