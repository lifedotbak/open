package com.spyker.framework.core;

import com.spyker.framework.response.RestMapResponse;
import com.spyker.framework.util.StringUtils;
import com.spyker.framework.util.date.DateUtils;
import lombok.Data;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * web层通用数据处理
 *
 * @author spyker
 */
public class BaseController {

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 返回成功消息
     */
    public RestMapResponse success(String message) {
        return RestMapResponse.success(message);
    }

    /**
     * 返回成功消息
     */
    public RestMapResponse success(Object data) {
        return RestMapResponse.success(data);
    }

    /**
     * 返回失败消息
     */
    public RestMapResponse error(String message) {
        return RestMapResponse.error(message);
    }

    /**
     * 返回警告消息
     */
    public RestMapResponse warn(String message) {
        return RestMapResponse.warn(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected RestMapResponse toAjax(int rows) {
        return rows > 0 ? RestMapResponse.success() : RestMapResponse.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected RestMapResponse toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public RestMapResponse success() {
        return RestMapResponse.success();
    }

    /**
     * 返回失败消息
     */
    public RestMapResponse error() {
        return RestMapResponse.error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

    @Data
    public static class SearchPageInfo {
        private Integer page = 1;
        private Integer size = 10;
    }
}