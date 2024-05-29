package com.spyker.framework.tencent.pay.v3.entity.pay;

import lombok.Data;

/**
 * 通知报文解析amount信息
 *
 * @author spyker
 */
@Data
public class PayNotifyCiphertextParseAmount {

    private int payer_total;
    private int total;
    private String currency;
    private String payer_currency;
}