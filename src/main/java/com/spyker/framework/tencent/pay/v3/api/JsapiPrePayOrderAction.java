package com.spyker.framework.tencent.pay.v3.api;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.spyker.framework.tencent.pay.PayConfig;
import com.spyker.framework.tencent.pay.v3.entity.CallBackAuth;
import com.spyker.framework.tencent.pay.v3.entity.RequestAuth;
import com.spyker.framework.tencent.pay.v3.entity.pay.JsapiPrePayOrderResult;
import com.spyker.framework.tencent.pay.v3.entity.pay.PrePayId;
import com.spyker.framework.tencent.pay.v3.entity.pay.PrePayOrder;
import com.spyker.framework.tencent.pay.v3.sign.SignCallBackUtil;
import com.spyker.framework.tencent.pay.v3.sign.SignRequestAuthUtil;
import com.spyker.framework.util.AppRandomStringUtil;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * 微信统一下单(个人到公司)
 *
 * 商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易会话标识后再在APP里面调起支付。
 *
 * @author zhangzhaofeng
 *
 */
@Slf4j
//@Component
public class JsapiPrePayOrderAction {

	public static final String url = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";

	private static final String http_method = "POST";

	@Autowired
	private PayConfig payConfig;

	@SneakyThrows
	public JsapiPrePayOrderResult execue(final PrePayOrder prePayOrder) {

		final SignRequestAuthUtil signRequestAuthUtil = new SignRequestAuthUtil(payConfig);
		final RequestAuth prePayAuth = create(prePayOrder);

		final HttpPost httpPost = new HttpPost(url);

		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Content-Type", "application/json");

		httpPost.addHeader("Authorization", signRequestAuthUtil.generateHttpHeader(prePayAuth));

		final StringEntity se = new StringEntity(prePayAuth.getBody(), "UTF-8");
		httpPost.setEntity(se);

		final HttpClient httpClient = HttpClients.createDefault();

		final HttpResponse httpResponse = httpClient.execute(httpPost);

		final HttpEntity httpEntity = httpResponse.getEntity();

		final String httpResult = EntityUtils.toString(httpEntity, "UTF-8");

		log.info(httpResult);

		final Gson gson = new Gson();

		final PrePayId prePayResult = gson.fromJson(httpResult, PrePayId.class);

		if (null != prePayResult && !StringUtils.isBlank(prePayResult.getPrepay_id())) {

			final JsapiPrePayOrderResult jsapiPrePayOrderResult = new JsapiPrePayOrderResult();

			// 应用id
			jsapiPrePayOrderResult.setAppid(payConfig.getAppId());

			// 订单详情扩展字符串
			jsapiPrePayOrderResult.setPackagevalue("prepay_id=" + prePayResult.getPrepay_id());

			// 随机字符串
			jsapiPrePayOrderResult.setNoncestr(prePayAuth.getNonce_str());
			// 时间戳
			jsapiPrePayOrderResult.setTimeStamp(prePayAuth.getTimestamp());
			// 签名

			// 签名
			signCallback(jsapiPrePayOrderResult);

			return jsapiPrePayOrderResult;

		}

		return null;
	}

	private JsapiPrePayOrderResult signCallback(final JsapiPrePayOrderResult jsapiPrePayOrderResult) {

		final CallBackAuth callBackAuth = new CallBackAuth();

		callBackAuth.setAppId(jsapiPrePayOrderResult.getAppid());
		callBackAuth.setNonceStr(jsapiPrePayOrderResult.getNoncestr());

		callBackAuth.setPackageValue(jsapiPrePayOrderResult.getPackagevalue());
		callBackAuth.setTimeStamp(jsapiPrePayOrderResult.getTimeStamp());

		final SignCallBackUtil signCallBackUtil = new SignCallBackUtil(payConfig);
		jsapiPrePayOrderResult.setPaySign(signCallBackUtil.jsapi(callBackAuth));

		return jsapiPrePayOrderResult;

	}

	private RequestAuth create(final PrePayOrder prePayOrder) {

		prePayOrder.setAppid(payConfig.getAppId());
		prePayOrder.setMchid(payConfig.getMchId());
		prePayOrder.setNotify_url(payConfig.getNotifyUrl());

		final Gson gson = new Gson();
		final String uos = gson.toJson(prePayOrder);

		final String nonceStr = AppRandomStringUtil.random32();
		final String timestamp = System.currentTimeMillis() / 1000 + "";

		final RequestAuth prePayAuthorizationInfo = new RequestAuth();

		prePayAuthorizationInfo.setBody(uos);
		prePayAuthorizationInfo.setCertificateSerialNo(payConfig.getCertificateSerialNo());
		prePayAuthorizationInfo.setMchid(payConfig.getMchId());
		prePayAuthorizationInfo.setMethod(http_method);
		prePayAuthorizationInfo.setNonce_str(nonceStr);
		prePayAuthorizationInfo.setUrl(url);
		prePayAuthorizationInfo.setTimestamp(timestamp);

		return prePayAuthorizationInfo;

	}
}