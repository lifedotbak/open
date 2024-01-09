package com.spyker.framework.third.zlmediakit.action;

import com.google.gson.Gson;
import com.spyker.framework.third.zlmediakit.ZLMediaKitProperties;
import com.spyker.framework.third.zlmediakit.model.OpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过fork FFmpeg进程的方式拉流代理，支持任意协议
 *
 * @author spyker
 */
@ConditionalOnClass(ZLMediaKitProperties.class)
@AutoConfiguration
@Slf4j
public class AddFFmpegSourceAction {

    private static final String method = "/index/api/addFFmpegSource";

    @Autowired private RestTemplate restTemplate;

    @Autowired private ZLMediaKitProperties zLMediaKitProperties;

    /**
     * 通过fork FFmpeg进程的方式拉流代理，支持任意协议
     *
     * @param srcUrl FFmpeg拉流地址,支持任意协议或格式(只要FFmpeg支持即可)
     * @param dstUrl FFmpeg rtmp推流地址，一般都是推给自己，例如rtmp://127.0.0.1/live/stream_form_ffmpeg
     *     rtsp://192.168.200.73:8554/live/171
     * @return
     */
    public OpResult execute(String srcUrl, String dstUrl) {

        String postUrl =
                "http://"
                        + zLMediaKitProperties.getIp()
                        + ":"
                        + zLMediaKitProperties.getPort()
                        + method;

        // 设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 设置Http的body
        Map<String, Object> body = new HashMap<>();

        body.put("secret", zLMediaKitProperties.getSecret());
        body.put("src_url", srcUrl);
        body.put("dst_url", dstUrl);
        body.put("timeout_ms", 10000);

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