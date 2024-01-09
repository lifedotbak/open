package com.spyker.framework.third.tencent.pay.v3.entity.refunds;

import lombok.Data;

/**
 * 退款请求返回结果
 *
 * @author spyker
 */
@Data
public class RefundsResult {

    /** 微信支付退款单号 string[1, 32] 是 微信支付退款单号 */
    private String refund_id;

    /** 商户退款单号 string[1, 64] 是 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。 */
    private String out_refund_no;

    /** 微信支付订单号 string[1, 32] 是 微信支付交易订单号 */
    private String transaction_id;

    /** 商户订单号 out_trade_no string[1, 32] 是 原支付交易对应的商户订单号 */
    private String out_trade_no;

    /**
     * 退款渠道 channel string[1, 16] 是 枚举值： ORIGINAL：原路退款 BALANCE：退回到余额 OTHER_BALANCE：原账户异常退到其他余额账户
     * OTHER_BANKCARD：原银行卡异常退到其他银行卡
     */
    private String channel;

    /**
     * 退款入账账户 user_received_account string[1, 64] 是 取当前退款单的退款入账方，有以下几种情况： 1）退回银行卡：{银行名称}{卡类型}{卡尾号}
     * 2）退回支付用户零钱:支付用户零钱 3）退还商户:商户基本账户商户结算银行账户 4）退回支付用户零钱通:支付用户零钱通
     */
    private String user_received_account;

    /** 退款成功时间 success_time string[1, 64] 否 退款成功时间，当退款状态为退款成功时有返回。 */
    private String success_time;

    /**
     * 退款状态 status string[1, 32] 是 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台-交易中心，手动处理此笔退款。 枚举值：
     * SUCCESS：退款成功 CLOSED：退款关闭 PROCESSING：退款处理中 ABNORMAL：退款异常
     */
    private String create_time;

    /**
     * 资金账户 funds_account string[1, 32] 否 退款所使用资金对应的资金账户类型 枚举值： UNSETTLED : 未结算资金 AVAILABLE : 可用余额
     * UNAVAILABLE : 不可用余额 OPERATION : 运营户 BASIC : 基本账户（含可用余额和不可用余额）
     */
    private String status;

    /**
     * 资金账户 funds_account string[1, 32] 否 退款所使用资金对应的资金账户类型 枚举值： UNSETTLED : 未结算资金 AVAILABLE : 可用余额
     * UNAVAILABLE : 不可用余额 OPERATION : 运营户 BASIC : 基本账户（含可用余额和不可用余额） 示例值：UNSETTLED
     */
    private String funds_account;

    /** 金额信息 amount object 是 金额详细信息 */
    private RefundsResultAmount amount;
}