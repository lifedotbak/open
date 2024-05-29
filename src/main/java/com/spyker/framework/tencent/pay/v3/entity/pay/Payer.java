package com.spyker.framework.tencent.pay.v3.entity.pay;

import lombok.Data;

@Data
public class Payer {

    /** 用户在直连商户appid下的唯一标识。 */
    private String openid;
}