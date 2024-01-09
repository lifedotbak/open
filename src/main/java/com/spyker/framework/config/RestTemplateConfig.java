package com.spyker.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * springboot项目需要开启此配置，springcloud项目无需此类，springcloud默认开启
 *
 * @author spyker
 */
@Configuration
public class RestTemplateConfig {

    @Autowired private RestTemplateBuilder builder;

    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
    @Bean
    public RestTemplate restTemplate() {

        Duration readTime = Duration.ofMinutes(1);
        Duration connectTime = Duration.ofMinutes(1);

        return builder.setReadTimeout(readTime).setConnectTimeout(connectTime).build();
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000); // ms
        factory.setConnectTimeout(15000); // ms
        return factory;
    }

    // 解决微信返回json Content-Type 值却是 text/plain 的问题
    public class WeChatMappingJackson2HttpMessageConverter
            extends MappingJackson2HttpMessageConverter {
        public WeChatMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            mediaTypes.add(MediaType.TEXT_HTML);
            setSupportedMediaTypes(mediaTypes);
        }
    }
}