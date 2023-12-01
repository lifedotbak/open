package com.spyker.framework.third.tencent.pay.v3.api;

import com.google.gson.Gson;
import com.spyker.framework.third.tencent.pay.PayConfig;
import com.spyker.framework.third.tencent.pay.v3.entity.pay.PayNotify;
import com.spyker.framework.third.tencent.pay.v3.entity.pay.PayNotifyCiphertextParse;
import com.spyker.framework.third.tencent.pay.v3.entity.pay.PayNotifyResource;
import com.spyker.framework.third.tencent.pay.v3.utils.AesUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@AutoConfiguration
@ConditionalOnClass(PayConfig.class)
@Slf4j
public class ParsePayNotifyAction {

    @Autowired
    private PayConfig payConfig;

    public static void main(String[] args) {
        String ss = "{\"mchid\":\"1627332486\",\"appid\":\"xxxxxx\"," + "\"out_trade_no" + "\":\"xxxxx\"," +
                "\"transaction_id\":\"xxxxx\"," + "\"trade_type\":\"JSAPI\"," + "\"trade_state\":\"SUCCESS\"," +
                "\"trade_state_desc\":\"支付成功\"," + "\"bank_type\":\"CMB_CREDIT\"," + "\"attach\":\"\"," +
                "\"success_time\":\"2022-07-25T09:41:49+08:00\"," + "\"payer\":{\"openid" +
                "\":\"odsEZ5TbgEfZqfNMRWkWKuEMgkI0\"},\"amount\":{\"total\":1,\"payer_total\":1," + "\"currency" +
                "\":\"CNY\",\"payer_currency\":\"CNY\"}}";

        Gson gson = new Gson();
        PayNotifyCiphertextParse result = gson.fromJson(ss, PayNotifyCiphertextParse.class);

        System.out.println(result);

    }

    @SneakyThrows
    public PayNotifyCiphertextParse parseResource(HttpServletRequest request) {

        Gson gson = new Gson();
        PayNotify payNotify = gson.fromJson(parseBody(request), PayNotify.class);

        log.info("payNotify-->{}", payNotify);

        // 如果支付成功
        if (null != payNotify && "TRANSACTION.SUCCESS".equals(payNotify.getEvent_type())) {
            //		if (null != payNotify) {

            PayNotifyResource resource = payNotify.getResource();

            String apiV3Key = payConfig.getApiV3key();

            // 解密后资源数据
            AesUtil aesUtil = new AesUtil(apiV3Key.getBytes(StandardCharsets.UTF_8));
            String notifyResourceStr = aesUtil.decryptToString(resource.getAssociated_data()
                                                                       .getBytes(StandardCharsets.UTF_8),
                                                               resource.getNonce().getBytes(StandardCharsets.UTF_8),
                                                               resource.getCiphertext());

            log.info("notifyResourceStr-->{}", notifyResourceStr);

            gson = new Gson();
            PayNotifyCiphertextParse result = gson.fromJson(notifyResourceStr, PayNotifyCiphertextParse.class);

            log.info("result-->{}", result);

            return result;

        }

        return null;

    }

    @SneakyThrows
    private String parseBody(HttpServletRequest request) {

        //		String nonce = request.getHeader(Wechatpay_Nonce);
        //		String timestamp = request.getHeader(Wechatpay_Timestamp);
        //		String signature = Base64Util.decodeBase64(request.getHeader(Wechatpay_Signature));

        //		log.info("Wechatpay_Nonce=" + nonce);
        //		log.info("Wechatpay_Timestamp=" + timestamp);
        //		log.info("Wechatpay_Signature=" + request.getHeader(Wechatpay_Signature));
        //		log.info("decodeBase64 Wechatpay_Signature=" + signature);

        /**
         * 应答报文主体,获取微信调用我们notify_url的返回信息
         */
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        //		String body = new String(outSteam.toByteArray(), "utf-8");
        String body = outSteam.toString();

        log.info("request body-->{}", body);

        return body;

    }

}