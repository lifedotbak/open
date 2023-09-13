package com.spyker.framework.zlmediakit.action;

import com.google.gson.Gson;
import com.spyker.framework.zlmediakit.OpResult;
import com.spyker.framework.zlmediakit.ZLMediaKitProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过fork FFmpeg进程的方式拉流代理，支持任意协议
 *
 * @author spyker
 */
@Component
@Slf4j
public class DelFFmpegSourceAction {

    private static final String method = "/index/api/delFFmpegSource";

    @Autowired
    private ZLMediaKitProperties zlMediaKitProperties;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 关闭ffmpeg拉流代理
     *
     * @param key
     * @return
     */
    public OpResult execute(String key) {

        String postUrl = "http://" + zlMediaKitProperties.getIp() + ":" + zlMediaKitProperties.getPort() + method;

        // 设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 设置Http的body
        Map<String, Object> body = new HashMap<>();

        body.put("secret", zlMediaKitProperties.getSecret());
        body.put("key", key);

        log.info("requestBody-->{}", body);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        OpResult result = null;

        try {

            ResponseEntity<String> exchangeResult = restTemplate.exchange(postUrl, HttpMethod.POST, entity,
                    String.class);

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