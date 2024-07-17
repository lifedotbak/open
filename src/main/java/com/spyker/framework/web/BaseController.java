package com.spyker.framework.web;

import com.spyker.framework.util.date.ExDateUtils;
import com.spyker.framework.util.text.ExStringUtils;
import com.spyker.framework.web.response.RestMapResponse;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * controller基础类,web层通用数据处理
 *
 * @author spyker
 */
public class BaseController {

    /** 将前台传递过来的日期格式的字符串，自动转化为Date类型 */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(
                Date.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String text) {
                        setValue(ExDateUtils.parse(text));
                    }
                });
    }

    /** 返回成功消息 */
    public RestMapResponse success(String message) {
        return RestMapResponse.success(message);
    }

    /** 返回成功消息 */
    public RestMapResponse success(Object data) {
        return RestMapResponse.success(data);
    }

    /** 返回失败消息 */
    public RestMapResponse error(String message) {
        return RestMapResponse.error(message);
    }

    /** 返回警告消息 */
    public RestMapResponse warn(String message) {
        return RestMapResponse.warn(message);
    }

    /** 返回成功 */
    public RestMapResponse success() {
        return RestMapResponse.success();
    }

    /** 返回失败消息 */
    public RestMapResponse error() {
        return RestMapResponse.error();
    }

    /** 页面跳转 */
    public String redirect(String url) {
        return ExStringUtils.format("redirect:{}", url);
    }
}