package com.spyker.framework.tencent.pay.v3.entity.refunds;

import lombok.Data;

/**
 * 退款回调通知结果信息
 *
 * @author spyker
 *
 */
@Data
public class RefundsNotify {

	/**
	 * 通知ID id string[1,36] 是 通知的唯一ID 示例值：EV-2018022511223320873
	 */
	private String id;

	/**
	 * 通知创建时间 create_time string[1,32] 是
	 * 通知创建的时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC
	 * 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
	 * 示例值：2018-06-08T10:34:56+08:00
	 */
	private String create_time;

	/**
	 * 通知类型 event_type string[1,32] 是 通知的类型： REFUND.SUCCESS：退款成功通知
	 * REFUND.ABNORMAL：退款异常通知 REFUND.CLOSED：退款关闭通知 示例值：REFUND.SUCCESS
	 */
	private String event_type;

	/**
	 * 通知简要说明 summary string[1,16] 是 通知简要说明 示例值：退款成功
	 */
	private String summary;

	/**
	 * 通知数据类型 resource_type string[1,32] 是 通知的资源数据类型，支付成功通知为encrypt-resource
	 * 示例值：encrypt-resource
	 */
	private String resource_type;

	/**
	 * -通知数据 resource object 是 通知资源数据 json格式，见示例
	 */
	private RefundsNotifyResource resource;
}
