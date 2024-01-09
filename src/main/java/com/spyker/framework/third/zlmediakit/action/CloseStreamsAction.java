package com.spyker.framework.third.zlmediakit.action;

import com.google.gson.Gson;
import com.spyker.framework.third.zlmediakit.ZLMediaKitProperties;
import com.spyker.framework.third.zlmediakit.model.OpResult;
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
public class CloseStreamsAction {

    private static final String method = "/index/api/close_streams";
    @Autowired private ZLMediaKitProperties zlMediaKitProperties;
    @Autowired private RestTemplate restTemplate;

    public OpResult execute(String vhost, String app, String stream, Boolean isForceClose) {

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
        //		body.put("schema", schema);
        if (StringUtils.isNotBlank(vhost)) {
            body.put("vhost", vhost);
        }
        if (StringUtils.isNotBlank(app)) {
            body.put("app", app);
        }
        if (StringUtils.isNotBlank(stream)) {
            body.put("stream", stream);
        }
        if (null != isForceClose && isForceClose) {
            body.put("force", "1");
        }

        log.info("requestBody-->{}", body);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        OpResult result = null;

        try {

            ResponseEntity<String> exchangeResult =
                    restTemplate.exchange(postUrl, HttpMethod.POST, entity, String.class);

            String responseBody = exchangeResult.getBody();

            log.info("responseBody--->{}", responseBody);

            Gson gson = new Gson();

            result = gson.fromJson(responseBody, OpResult.class);

            log.info("opResult--->{}", result);

        } catch (Exception e) {

            log.error("e-->{}", e);
        }

        return result;
    }
}