package com.spyker.framework.tencent.pay.v3.entity;

import lombok.Data;

/***
 * 微信接口请求时候,创建sign数据对象.比如预支付
 *
 * @author zhangzhaofeng
 *
 */
@Data
public class RequestAuth {

	private String nonce_str;
	private String url;
	private String timestamp;
	private String mchid;
	private String certificateSerialNo;
	private String method;
	private String body;
}
