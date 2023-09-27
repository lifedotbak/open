package com.spyker.framework.tencent.pay.v3.entity.refunds;

import lombok.Data;

/**
 * 退款通知资源数据
 *
 * @author spyker
 */
@Data
public class RefundsNotifyResource {

    /**
     * 加密算法类型 algorithm string[1,32] 是 对开启结果数据进行加密的加密算法，目前只支持AEAD_AES_256_GCM
     * 示例值：AEAD_AES_256_GCM
     */
    private String algorithm;

    /**
     * 加密前的对象类型 original_type string[1,32] 是 加密前的对象类型，退款通知的类型为refund 示例值：refund
     */
    private String original_type;

    /**
     * 数据密文 ciphertext string[1,1048576] 是 Base64编码后的开启/停用结果数据密文
     * 示例值：fdasfsadsadsalkja484w
     */
    private String ciphertext;

    /**
     * 随机串 nonce string[1,16] 是 加密使用的随机串 示例值：fdasfjihihihlkja484w
     */
    private String nonce;

    /**
     * 附加数据
     */
    private String associated_data;
}