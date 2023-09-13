package com.spyker.framework.tencent.pay;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "weixin.pay")
@Component
@Data
public class PayConfig {

	/**
	 * V3 支付信息
	 */
	private String appId;
	private String mchId;
	private String certificateSerialNo;
	private String apiclientKeyPath;
	private String apiV3key;

	private String notifyUrl;
	private String refundNotifyUrl;

}
