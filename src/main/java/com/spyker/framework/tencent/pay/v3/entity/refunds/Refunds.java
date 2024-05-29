package com.spyker.framework.tencent.pay.v3.entity.refunds;

import lombok.Data;

import java.util.List;

/**
 * 退款请求对象
 *
 * @author spyker
 */
@Data
public class Refunds {

    /** 微信支付订单号 string[1, 32] (微信支付订单号,商户订单号二选一) 原支付交易对应的微信订单号 */
    private String transaction_id;

    /** 商户订单号 string[6, 32] (微信支付订单号,商户订单号二选一) 原支付交易对应的商户订单号 */
    private String out_trade_no;

    /** 商户退款单号 string[1, 64] 是 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。 */
    private String out_refund_no;

    /** 退款原因 否 若商户传入，会在下发给用户的退款消息中体现退款原因 */
    private String reason;

    /**
     * 退款结果回调url 否 异步接收微信支付退款结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效，优先回调当前传的这个地址。
     */
    private String notify_url;

    /** 退款资金来源 否 若传递此参数则使用对应的资金账户退款，否则默认使用未结算资金退款（仅对老资金流商户适用） 枚举值： AVAILABLE：可用余额账户 */
    private String funds_account;

    /** 订单金额信息 是 body订单金额信息 */
    private RefundsAmount amount;

    /** array 否 body指定商品退款需要传此参数，其他场景无需传递 */
    private List<RefundsGoodsDetail> goods_detail;
}