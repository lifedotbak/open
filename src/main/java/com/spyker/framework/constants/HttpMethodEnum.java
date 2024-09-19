package com.spyker.framework.constants;

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求方式
 *
 * @author spyker
 */
public enum HttpMethodEnum {
    GET,

    HEAD,

    POST,

    PUT,

    PATCH,

    DELETE,

    OPTIONS,

    TRACE;

    private static final Map<String, HttpMethodEnum> mappings = new HashMap<>(16);

    static {
        for (HttpMethodEnum httpMethod : values()) {
            mappings.put(httpMethod.name(), httpMethod);
        }
    }

    public boolean matches(String method) {
        return (this == resolve(method));
    }

    @Nullable
    public static HttpMethodEnum resolve(@Nullable String method) {
        return (method != null ? mappings.get(method) : null);
    }
}