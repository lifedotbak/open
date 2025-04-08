package com.spyker.framework.tencent.pay.v3.entity.pay;

import lombok.Data;

@Data
public class JsapiPrePayOrderResult {

    /** 请填写merchant_appid对应的值。小程序id/公众号id */
    private String appid;

    /** 时间戳 */
    private String timeStamp;

    /** 随机字符串 ，不长于32位 */
    private String noncestr;

    /**
     * 订单详情扩展字符串
     *
     * <p>统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
     * 示例值：prepay_id=wx201410272009395522657a690389285100
     */
    private String packagevalue;

    /** 签名方式 */
    private String signType = "RSA";

    /** 签名，使用字段appId、timeStamp、nonceStr、prepayid按照签名生成算法计算得出的签名值 */
    private String paySign;
}
