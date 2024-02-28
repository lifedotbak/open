package com.spyker.framework.third.tencent.pay.v3.api;

import com.google.gson.Gson;
import com.spyker.framework.third.tencent.pay.PayConfig;
import com.spyker.framework.third.tencent.pay.v3.entity.CallBackAuth;
import com.spyker.framework.third.tencent.pay.v3.entity.RequestAuth;
import com.spyker.framework.third.tencent.pay.v3.entity.pay.AppPrePayOrderResult;
import com.spyker.framework.third.tencent.pay.v3.entity.pay.PrePayId;
import com.spyker.framework.third.tencent.pay.v3.entity.pay.PrePayOrder;
import com.spyker.framework.third.tencent.pay.v3.sign.SignCallBackUtil;
import com.spyker.framework.third.tencent.pay.v3.sign.SignRequestAuthUtil;
import com.spyker.framework.util.ExRandomStringUtils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * 微信统一下单(个人到公司)
 *
 * <p>商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易会话标识后再在APP里面调起支付。
 *
 * @author zhangzhaofeng
 */
@Slf4j
@AutoConfiguration
@ConditionalOnClass(PayConfig.class)
public class AppPrePayOrderAction {

    public static final String url = "https://api.mch.weixin.qq.com/v3/pay/transactions/app";

    private static final String http_method = "POST";

    @Autowired private PayConfig payConfig;

    @SneakyThrows
    public AppPrePayOrderResult execue(PrePayOrder prePayOrder) {

        SignRequestAuthUtil signRequestAuthUtil = new SignRequestAuthUtil(payConfig);

        RequestAuth prePayAuth = create(prePayOrder);

        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization", signRequestAuthUtil.generateHttpHeader(prePayAuth));

        StringEntity se = new StringEntity(prePayAuth.getBody(), "UTF-8");
        httpPost.setEntity(se);

        HttpClient httpClient = HttpClients.createDefault();

        HttpResponse httpResponse = httpClient.execute(httpPost);

        HttpEntity httpEntity = httpResponse.getEntity();

        String httpResult = EntityUtils.toString(httpEntity, "UTF-8");

        log.info(httpResult);

        Gson gson = new Gson();

        PrePayId prePayResult = gson.fromJson(httpResult, PrePayId.class);

        if (null != prePayResult && !StringUtils.isBlank(prePayResult.getPrepay_id())) {

            AppPrePayOrderResult appPrePayOrderResult = new AppPrePayOrderResult();

            // 应用id
            appPrePayOrderResult.setAppId(payConfig.getAppId());
            // 商户号
            appPrePayOrderResult.setPartnerId(payConfig.getMchId());
            // 预支付交易会话ID
            appPrePayOrderResult.setPrepayId(prePayResult.getPrepay_id());
            // 订单详情扩展字符串
            appPrePayOrderResult.setPackageValue("Sign=WXPay");
            // 随机字符串
            appPrePayOrderResult.setNonceStr(prePayAuth.getNonce_str());
            // 时间戳
            appPrePayOrderResult.setTimestamp(prePayAuth.getTimestamp());
            // 签名
            signCallback(appPrePayOrderResult);

            log.info("appPrePayOrderResult ->{}", appPrePayOrderResult);

            return appPrePayOrderResult;
        }

        return null;
    }

    private RequestAuth create(PrePayOrder prePayOrder) {

        prePayOrder.setAppid(payConfig.getAppId());
        prePayOrder.setMchid(payConfig.getMchId());
        prePayOrder.setNotify_url(payConfig.getNotifyUrl());

        Gson gson = new Gson();
        String uos = gson.toJson(prePayOrder);

        String nonceStr = ExRandomStringUtils.random32();
        String timestamp = System.currentTimeMillis() / 1000 + "";

        RequestAuth prePayAuthorizationInfo = new RequestAuth();

        prePayAuthorizationInfo.setBody(uos);
        prePayAuthorizationInfo.setCertificateSerialNo(payConfig.getCertificateSerialNo());
        prePayAuthorizationInfo.setMchid(payConfig.getMchId());
        prePayAuthorizationInfo.setMethod(http_method);
        prePayAuthorizationInfo.setNonce_str(nonceStr);
        prePayAuthorizationInfo.setUrl(url);
        prePayAuthorizationInfo.setTimestamp(timestamp);

        return prePayAuthorizationInfo;
    }

    private AppPrePayOrderResult signCallback(AppPrePayOrderResult appPrePayOrderResult) {

        CallBackAuth appCallBackAuth = new CallBackAuth();

        appCallBackAuth.setAppId(appPrePayOrderResult.getAppId());
        appCallBackAuth.setNonceStr(appPrePayOrderResult.getNonceStr());

        appCallBackAuth.setPrepayid(appPrePayOrderResult.getPrepayId());
        appCallBackAuth.setTimeStamp(appPrePayOrderResult.getTimestamp());

        SignCallBackUtil signCallBackUtils = new SignCallBackUtil(payConfig);
        appPrePayOrderResult.setPaySign(signCallBackUtils.app(appCallBackAuth));

        return appPrePayOrderResult;
    }
}