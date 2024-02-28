package com.spyker.framework.third.tencent.pay.v3.sign;

import com.spyker.framework.third.tencent.pay.PayConfig;
import com.spyker.framework.third.tencent.pay.v3.entity.RequestAuth;
import com.spyker.framework.third.tencent.pay.v3.utils.PrivateKeyUtil;

import lombok.SneakyThrows;

import okhttp3.HttpUrl;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

/**
 * 签名生成 商户可以按照下述步骤生成请求的签名。 创建微信支付请求签名
 *
 * <p>https://wechatpay-api.gitbook.io/wechatpay-api-v3/qian-ming-zhi-nan-1/qian-ming-sheng-cheng
 *
 * <p>// Authorization: <schema> <token> // GET - getToken("GET", httpurl, "") // POST -
 * getToken("POST", httpurl, json)
 *
 * <p>示例：
 *
 * <p>Authorization: WECHATPAY2-SHA256-RSA2048
 * mchid="1900009191",nonce_str="593BEC0C930BF1AFEB40B4A08C8FB242",
 * signature="uOVRnA4qG/MNnYzdQxJanN+zU+lTgIcnU9BxGw5dKjK+VdEUz2FeIoC+D5sB/LN
 * +nGzX3hfZg6r5wT1pl2ZobmIc6p0ldN7J6yDgUzbX8Uk3sD4a4eZVPTBvqNDoUqcYMlZ9uuDdCvNv4TM3c1WzsXUrExwVkI1XO5jCNbgDJ25nkT
 * /c1gIFvqoogl7MdSFGc4W4xZsqCItnqbypR3RuGIlR9h9vlRsy7zJR9PBI83X8alLDIfR1ukt1P7tMnmogZ0cuDY8cZsd8ZlCgLadmvej58SLsIkVxFJ8XyUgx9FmutKSYTmYtWBZ0+tNvfGmbXU7cob8H/4nLBiCwIUFluw==",timestamp="1554208460",serial_no="1DDE55AD98ED71D6EDD4A4A16996DE7B47773A8C"
 *
 * @author zhangzhaofeng
 */
public class SignRequestAuthUtil {

    public static final String schema = "WECHATPAY2-SHA256-RSA2048";

    private final PayConfig payConfig;

    public SignRequestAuthUtil(PayConfig payConfig) {
        this.payConfig = payConfig;
    }

    /**
     * 生成http头信息
     *
     * @param method
     * @param url
     * @param body json格式
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public String generateHttpHeader(RequestAuth prePayAuth) {
        return schema + " " + generateToken(prePayAuth);
    }

    /**
     * 生成token信息
     *
     * @param method
     * @param url
     * @param body
     * @return
     * @throws Exception
     */
    @SneakyThrows
    private String generateToken(RequestAuth requestAuth) {
        String nonceStr = requestAuth.getNonce_str();

        String message = buildMessage(requestAuth);
        String signature = sign(message.getBytes(StandardCharsets.UTF_8));

        return "mchid=\""
                + payConfig.getMchId()
                + "\","
                + "nonce_str=\""
                + nonceStr
                + "\","
                + "timestamp=\""
                + requestAuth.getTimestamp()
                + "\","
                + "serial_no=\""
                + payConfig.getCertificateSerialNo()
                + "\","
                + "signature=\""
                + signature
                + "\"";
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
    private String buildMessage(RequestAuth prePayAuth) {

        HttpUrl httpurl = HttpUrl.parse(prePayAuth.getUrl());

        StringBuilder canonicalUrl = new StringBuilder(httpurl.encodedPath());
        if (httpurl.encodedQuery() != null) {
            canonicalUrl.append("?").append(httpurl.encodedQuery());
        }

        return prePayAuth.getMethod()
                + "\n"
                + canonicalUrl
                        .append("\n")
                        .append(prePayAuth.getTimestamp())
                        .append("\n")
                        .append(prePayAuth.getNonce_str())
                        .append("\n")
                        .append(prePayAuth.getBody())
                        .append("\n");
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
}