package com.spyker.framework.third.zlmediakit.action;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.spyker.framework.third.zlmediakit.ZLMediaKitProperties;

import jodd.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@ConditionalOnClass(ZLMediaKitProperties.class)
@AutoConfiguration
@Slf4j
public class GetMediaListAction {

    private static final String method = "/index/api/getMediaList";

    @Autowired private ZLMediaKitProperties zlMediaKitProperties;

    @Autowired private RestTemplate restTemplate;

    public JSONObject execute(String vhost, String app, String stream) {

        String postUrl =
                "http://"
                        + zlMediaKitProperties.getIp()
                        + ":"
                        + zlMediaKitProperties.getPort()
                        + method;

        // 设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 设置Http的body
        Map<String, String> body = new HashMap<>();

        body.put("secret", zlMediaKitProperties.getSecret());
        body.put("vhost", vhost);
        body.put("app", app);
        if (StringUtil.isNotBlank(stream)) {
            body.put("stream", stream);
        }

        log.info("requestBody-->{}", body);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> exchangeResult =
                restTemplate.exchange(postUrl, HttpMethod.POST, entity, String.class);

        String responseBody = exchangeResult.getBody();

        log.info("responseBody--->{}", responseBody);

        JSONObject jsonObject = null;

        if (StringUtils.isNotBlank(responseBody)) {
            jsonObject = JSON.parseObject(responseBody);
        }

        return jsonObject;
    }
}