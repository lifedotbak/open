package com.spyker.framework.tencent.pay.v3.entity.pay;

import lombok.Data;

/**
 * 通知资源数据
 */
@Data
public class PayNotifyResource {

    /**
     * 加密算法类型
     */
    private String algorithm;

    /**
     * 数据密文
     */
    private String ciphertext;

    /**
     * 附加数据
     */
    private String associated_data;

    /**
     * 随机串
     */
    private String nonce;
}