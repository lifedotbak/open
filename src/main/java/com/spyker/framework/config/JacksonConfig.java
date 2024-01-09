package com.spyker.framework.config;

import java.math.BigDecimal;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/** Jackjson配置组件 */
@Configuration
public class JacksonConfig {

    /**
     * Jackson全局转化BigDecimal类型为String，解决jackson序列化时BigDecimal类型缺失精度问题
     *
     * @return Jackson2ObjectMapperBuilderCustomizer 注入的对象
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.serializerByType(
                        BigDecimal.class, ToStringSerializer.instance);
    }
}