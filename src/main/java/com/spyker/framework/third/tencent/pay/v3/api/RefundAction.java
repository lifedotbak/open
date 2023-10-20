package com.spyker.framework.third.tencent.pay.v3.api;

import com.google.gson.Gson;
import com.spyker.framework.third.tencent.pay.PayConfig;
import com.spyker.framework.third.tencent.pay.v3.entity.RequestAuth;
import com.spyker.framework.third.tencent.pay.v3.entity.refunds.Refunds;
import com.spyker.framework.third.tencent.pay.v3.entity.refunds.RefundsResult;
import com.spyker.framework.third.tencent.pay.v3.sign.SignRequestAuthUtil;
import com.spyker.framework.util.AppRandomStringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
 * 申请退款API
 * <p>
 * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_9.shtml
 *
 * @author spyker
 */
@Slf4j
@AutoConfiguration
@ConditionalOnClass(PayConfig.class)
public class RefundAction {

    public static final String url = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";

    private static final String http_method = "POST";

    @Autowired
    private PayConfig payConfig;

    @SneakyThrows
    public RefundsResult execue(Refunds refunds) {

        final SignRequestAuthUtil signRequestAuthUtil = new SignRequestAuthUtil(payConfig);
        final RequestAuth prePayAuth = create(refunds);

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

        log.info("httpResult -->{}", httpResult);

        final Gson gson = new Gson();

        final RefundsResult refundResult = gson.fromJson(httpResult, RefundsResult.class);

        log.info("refundResult -->{}", refundResult);

        return refundResult;

    }

    private RequestAuth create(Refunds refundsr) {

        final Gson gson = new Gson();
        final String uos = gson.toJson(refundsr);

        final String nonceStr = AppRandomStringUtils.random32();
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