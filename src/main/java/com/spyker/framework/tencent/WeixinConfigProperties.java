package com.spyker.framework.tencent;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(value = "weixin", ignoreInvalidFields = true)
public class WeixinConfigProperties {

    private Miniprogram miniprogram;
    private Pay pay;

    @Data
    private class Miniprogram {

        private String appId;
        private String appSecret;
    }

    @Data
    private class Pay {
        /** V3 支付信息 */
        private String appId;

        private String mchId;
        private String certificateSerialNo;
        private String apiclientKeyPath;
        private String apiV3key;

        private String notifyUrl;
        private String refundNotifyUrl;
    }
}