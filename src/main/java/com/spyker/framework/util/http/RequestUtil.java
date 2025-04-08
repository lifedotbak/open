package com.spyker.framework.util.http;

import com.spyker.framework.util.text.ExStringUtils;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/** Request工具类 */
public class RequestUtil extends HttpServlet {

    /** serialVersionUID */
    private static final long serialVersionUID = 2492714953241153027L;

    public static HashMap<String, Object> getRequestParamAndHeader() {

        try {
            HttpServletRequest request = getRequest();
            if (request == null) {
                return null;
            }

            // request信息
            HashMap<String, Object> data = new HashMap<>();
            HashMap<String, Object> requestParams = new HashMap<>();
            Enumeration<String> paraNames = request.getParameterNames();
            if (paraNames != null) {
                for (Enumeration<String> enumeration = paraNames; enumeration.hasMoreElements(); ) {
                    String key = enumeration.nextElement();
                    requestParams.put(key, request.getParameter(key));
                }
            }

            HashMap<String, Object> requestFilter = new HashMap<>();
            Enumeration<String> attributeNames = request.getAttributeNames();
            if (attributeNames != null) {
                for (Enumeration<String> attributeNames1 = attributeNames;
                        attributeNames1.hasMoreElements(); ) {
                    String key = attributeNames1.nextElement();
                    if (key.contains("request_")) {
                        requestFilter.put(key, request.getAttribute(key));
                    }
                }
            }

            data.put("url", request.getRequestURL());
            data.put("uri", request.getRequestURI());
            data.put("method", request.getMethod());
            data.put("request", requestParams);
            data.put("request_filter", requestFilter);

            // header 信息
            Enumeration<String> headerNames = request.getHeaderNames();
            HashMap<String, Object> headerParams = new HashMap<>();
            if (headerNames != null) {
                for (Enumeration<String> enumeration = headerNames;
                        enumeration.hasMoreElements(); ) {
                    String key = enumeration.nextElement();
                    String value = request.getHeader(key);
                    headerParams.put(key, value);
                }
            }
            data.put("header", headerParams);

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HttpServletRequest getRequest() {
        if (RequestContextHolder.getRequestAttributes() != null) {
            return ((ServletRequestAttributes)
                            Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                    .getRequest();
        }
        return null;
    }

    public static String getDomain() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return request.getServerName() + ":" + request.getServerPort();
    }

    public static String getUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        List<String> list = ExStringUtils.stringToArrayStrRegex(uri, "/");
        list.removeIf(StringUtils::isNumeric); // 去掉url中的数字参数
        list.removeIf(c -> c.contains(",")); // 去掉url中的逗号分隔参数
        return StringUtils.join(list, "/");
    }
}
