package com.spyker.framework.third.tencent.pay.v3.api;

import com.google.gson.Gson;
import com.spyker.framework.third.tencent.pay.PayConfig;
import com.spyker.framework.third.tencent.pay.v3.entity.refunds.RefundsCiphertextParse;
import com.spyker.framework.third.tencent.pay.v3.entity.refunds.RefundsNotify;
import com.spyker.framework.third.tencent.pay.v3.entity.refunds.RefundsNotifyResource;
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

/**
 * 退款返回结果解析
 *
 * @author spyker
 */
@AutoConfiguration
@ConditionalOnClass(PayConfig.class)
@Slf4j
public class ParseRefundNotifyAction {

    @Autowired private PayConfig payConfig;

    @SneakyThrows
    public RefundsCiphertextParse parseResource(HttpServletRequest request) {

        Gson gson = new Gson();
        RefundsNotify notify = gson.fromJson(parseBody(request), RefundsNotify.class);

        log.info("RefundsNotify --> {}", notify);

        // 如果退款成功
        if (null != notify && "REFUND.SUCCESS".equals(notify.getEvent_type())) {

            RefundsNotifyResource resource = notify.getResource();

            String apiV3Key = payConfig.getApiV3key();

            // 解密后资源数据
            AesUtil aesUtil = new AesUtil(apiV3Key.getBytes(StandardCharsets.UTF_8));
            String notifyResourceStr =
                    aesUtil.decryptToString(
                            resource.getAssociated_data().getBytes(StandardCharsets.UTF_8),
                            resource.getNonce().getBytes(StandardCharsets.UTF_8),
                            resource.getCiphertext());

            RefundsCiphertextParse result =
                    gson.fromJson(notifyResourceStr, RefundsCiphertextParse.class);

            log.info("RefundsCiphertextParse --> {}", result);

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

        /** 应答报文主体,获取微信调用我们notify_url的返回信息 */
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

        return body;
    }
}