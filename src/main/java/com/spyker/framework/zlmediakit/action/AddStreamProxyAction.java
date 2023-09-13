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
 * 动态添加rtsp/rtmp/hls拉流代理(只支持H264/H265/aac/G711负载)
 *
 * @author spyker
 */
@Component
@Slf4j
public class AddStreamProxyAction {

    private static final String method = "/index/api/addStreamProxy";
    @Autowired
    private ZLMediaKitProperties zlMediaKitProperties;
    @Autowired
    private RestTemplate restTemplate;

    public OpResult execute(String vhost, String app, String stream, String url) {

        String postUrl = "http://" + zlMediaKitProperties.getIp() + ":" + zlMediaKitProperties.getPort() + method;

        // 设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 设置Http的body
        Map<String, Object> body = new HashMap<>();

        body.put("secret", zlMediaKitProperties.getSecret());
        body.put("vhost", vhost);
        body.put("app", app);
        body.put("stream", stream);
        body.put("url", url);
        body.put("rtp_type", 0);
        body.put("enable_hls", 0);
        body.put("enable_mp4", 0);
        //        body.put("enable_rtsp", 0);
        body.put("enable_ts", 0);
        body.put("enable_fmp4", 0);

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