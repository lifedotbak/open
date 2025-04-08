package com.spyker.framework.tencent.pay.v3.entity.pay;

import lombok.Data;

/** APP调起支付对象 */
@Data
public class AppPrePayOrderResult {

    // 应用id
    private String appId;
    // 商户号
    private String partnerId;
    // 预支付交易会话ID
    private String prepayId;
    // 订单详情扩展字符串 暂填写固定值Sign=WXPay
    private String packageValue = "Sign=WXPay";
    // 随机字符串 ，不长于32位
    private String nonceStr;
    // 时间戳
    private String timestamp;
    // 签名，使用字段appId、timeStamp、nonceStr、prepayid按照签名生成算法计算得出的签名值
    private String paySign;
}
