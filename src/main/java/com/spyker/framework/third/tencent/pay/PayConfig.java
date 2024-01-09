package com.spyker.framework.third.tencent.pay;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "weixin.pay")
@Component
@Data
@ConditionalOnProperty(prefix = "weixin", name = "enabled", havingValue = "true")
public class PayConfig {

    /** V3 支付信息 */
    private String appId;

    private String mchId;
    private String certificateSerialNo;
    private String apiclientKeyPath;
    private String apiV3key;

    private String notifyUrl;
    private String refundNotifyUrl;
}