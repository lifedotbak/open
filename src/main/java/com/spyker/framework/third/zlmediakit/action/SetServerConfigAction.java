package com.spyker.framework.third.zlmediakit.action;

import com.google.gson.Gson;
import com.spyker.framework.third.zlmediakit.ZLMediaKitProperties;
import com.spyker.framework.third.zlmediakit.model.OpResult;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@ConditionalOnClass(ZLMediaKitProperties.class)
@AutoConfiguration
@Slf4j
public class SetServerConfigAction {

    private static final String method = "/index/api/setServerConfig";

    @Autowired private RestTemplate restTemplate;

    @Value("${zlmediakit.ip}")
    private String ip;

    @Value("${zlmediakit.port}")
    private int port;

    @Value("${zlmediakit.secret}")
    private String secret;

    public OpResult execute() {

        String postUrl = "http://" + ip + ":" + port + method;

        // 设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 设置Http的body
        Map<String, Object> body = new HashMap<>();

        body.put("secret", secret);
        //		body.put("ffmpeg.cmd", "%s -re -i %s -c:a aac -strict -2 -ar 44100 -ab 48k -c:v libx264
        // -f flv %s");
        body.put("ffmpeg.cmd", "%s -re -i %s -vcodec h264 -f rtsp -rtsp_transport tcp %s");

        log.info("requestBody-->{}", body);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

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