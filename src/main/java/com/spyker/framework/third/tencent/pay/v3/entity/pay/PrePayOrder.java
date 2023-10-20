package com.spyker.framework.third.tencent.pay.v3.entity.pay;

import lombok.Data;

@Data
public class PrePayOrder {

    /**
     * 直连商户申请的公众号或移动应用appid。max:32
     */
    private String appid;

    /**
     * 直连商户的商户号，由微信支付生成并下发。max:32
     */
    private String mchid;

    /**
     * 商品描述.max:127
     */
    private String description;

    /**
     * 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一，详见【商户订单号】。 max:32
     */
    private String out_trade_no;

    /**
     * 通知URL必须为直接可访问的URL，不允许携带查询串。 格式：URL
     * 示例值：https://www.weixin.qq.com/wxpay/pay.php
     */
    private String notify_url;

    /**
     * 附加数据 string[1,128] 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     */
    private String attach;

    private PrePayOrderAmount amount;

    /**
     * 用户标识 否 String(128) oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。
     */
    private Payer payer;
}