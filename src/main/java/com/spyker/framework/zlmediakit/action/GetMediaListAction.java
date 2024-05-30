package com.spyker.framework.zlmediakit.action;

import com.google.gson.Gson;
import com.spyker.framework.zlmediakit.ZLMediaKitProperties;
import com.spyker.framework.zlmediakit.model.OpGetMediaListResult;

import jodd.util.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@ConditionalOnClass(ZLMediaKitProperties.class)
@AutoConfiguration
@Slf4j
@RequiredArgsConstructor
public class GetMediaListAction {

    private static final String method = "/index/api/getMediaList";

    private final ZLMediaKitProperties zlMediaKitProperties;

    private final RestTemplate restTemplate;

    public OpGetMediaListResult execute(String vhost, String app, String stream) {

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

        OpGetMediaListResult result = null;

        if (StringUtils.isNotBlank(responseBody)) {
            Gson gson = new Gson();

            result = gson.fromJson(responseBody, OpGetMediaListResult.class);

            log.info("opGetMediaListResult--->{}", result);
        }

        return result;
    }
}