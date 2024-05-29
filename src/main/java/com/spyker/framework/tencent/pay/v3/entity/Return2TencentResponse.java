package com.spyker.framework.tencent.pay.v3.entity;

import lombok.Data;

/**
 * 微信支付回调之后,业务处理结果,返回给微信
 *
 * @author zhangzhaofeng
 */
@Data
public class Return2TencentResponse {

    /** 返回状态码 code string[1,32] 是 错误码，SUCCESS为清算机构接收成功，其他错误码为失败。 示例值：FAIL */
    private String code;

    /** 返回信息，如非空，为错误原因。 示例值：失败 */
    private String message;
}