package com.spyker.framework.tencent.pay.v3.entity.refunds;

import lombok.Data;

/**
 * 退款商品
 *
 * @author spyker
 */
@Data
public class RefundsGoodsDetail {

    /** 商户侧商品编码 string[1, 32] 是 由半角的大小写字母、数字、中划线、下划线中的一种或几种组成 */
    private String merchant_goods_id;

    /** 微信支付商品编码 string[1, 32] 否 微信支付定义的统一商品编号（没有可不传） */
    private String wechatpay_goods_id;

    /** 商品名称 string[1, 256] 否 商品的实际名称 */
    private String goods_name;

    /** 商品单价 int 是 商品单价金额，单位为分 */
    private int unit_price;

    /** 商品退款金额 int 是 商品退款金额，单位为分 */
    private int refund_amount;

    /** 商品退货数量 int 是 单品的退款数量 */
    private int refund_quantity;
}