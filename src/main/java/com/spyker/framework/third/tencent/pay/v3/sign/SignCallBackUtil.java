package com.spyker.framework.third.tencent.pay.v3.sign;

import com.spyker.framework.third.tencent.pay.PayConfig;
import com.spyker.framework.third.tencent.pay.v3.entity.CallBackAuth;
import com.spyker.framework.third.tencent.pay.v3.utils.PrivateKeyUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

/**
 * 生成回调的sign
 *
 * @author zhangzhaofeng
 */
@Slf4j
public class SignCallBackUtil {

    private final PayConfig payConfig;

    public SignCallBackUtil(PayConfig payConfig) {
        this.payConfig = payConfig;
    }

    @SneakyThrows
    public String app(CallBackAuth callBackAuth) {

        String message = buildAppMessage(callBackAuth);

        log.info("signApp message============>{}", message);

        String signature = sign(message.getBytes(StandardCharsets.UTF_8));

        log.info("signApp signature============>{}", signature);

        return signature;
    }

    /**
     * 构造的请求签名串
     *
     * @param method
     * @param url
     * @param timestamp
     * @param nonceStr
     * @param body
     * @return
     */
    private String buildAppMessage(CallBackAuth callBackAuth) {

        return callBackAuth.getAppId()
                + "\n"
                + callBackAuth.getTimeStamp()
                + "\n"
                + callBackAuth.getNonceStr()
                + "\n"
                + callBackAuth.getPrepayid()
                + "\n";
    }

    /**
     * 计算签名值
     *
     * @param message
     * @return
     * @throws Exception
     */
    @SneakyThrows
    private String sign(byte[] message) {

        PrivateKey privateKey = PrivateKeyUtil.getPrivateKey(payConfig.getApiclientKeyPath());

        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(message);

        return Base64.getEncoder().encodeToString(sign.sign());
    }

    @SneakyThrows
    public String jsapi(CallBackAuth callBackAuth) {

        String message = buildJsapiMessage(callBackAuth);

        log.info("signJsapi message============>{}", message);

        String signature = sign(message.getBytes(StandardCharsets.UTF_8));

        log.info("signJsapi signature============>{}", signature);

        return signature;
    }

    /**
     * 构造的请求签名串
     *
     * @param method
     * @param url
     * @param timestamp
     * @param nonceStr
     * @param body
     * @return
     */
    private String buildJsapiMessage(CallBackAuth callBackAuth) {

        return callBackAuth.getAppId()
                + "\n"
                + callBackAuth.getTimeStamp()
                + "\n"
                + callBackAuth.getNonceStr()
                + "\n"
                + callBackAuth.getPackageValue()
                + "\n";
    }
}