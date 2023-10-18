package com.spyker.framework.response;

import com.spyker.framework.constant.HttpStatus;
import com.spyker.framework.util.StringUtils;

import java.util.HashMap;
import java.util.Objects;

/**
 * 操作消息提醒
 *
 * @author platform
 */
public class RestMapResponse extends HashMap<String, Object> {

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    private RestMapResponse() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    private RestMapResponse(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    private RestMapResponse(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static RestMapResponse success() {
        return RestMapResponse.success("SUCCESS");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static RestMapResponse success(Object data) {
        return RestMapResponse.success("SUCCESS", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static RestMapResponse success(String msg) {
        return RestMapResponse.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static RestMapResponse success(String msg, Object data) {
        return new RestMapResponse(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static RestMapResponse warn(String msg) {
        return RestMapResponse.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static RestMapResponse warn(String msg, Object data) {
        return new RestMapResponse(HttpStatus.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static RestMapResponse error() {
        return RestMapResponse.error("ERROR");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static RestMapResponse error(String msg) {
        return RestMapResponse.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static RestMapResponse error(String msg, Object data) {
        return new RestMapResponse(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 错误消息
     */
    public static RestMapResponse error(int code, String msg) {
        return new RestMapResponse(code, msg, null);
    }

    /**
     * 是否为成功消息
     *
     * @return 结果
     */
    public boolean isSuccess() {
        return Objects.equals(HttpStatus.SUCCESS, this.get(CODE_TAG));
    }

    /**
     * 是否为警告消息
     *
     * @return 结果
     */
    public boolean isWarn() {
        return Objects.equals(HttpStatus.WARN, this.get(CODE_TAG));
    }

    /**
     * 是否为错误消息
     *
     * @return 结果
     */
    public boolean isError() {
        return Objects.equals(HttpStatus.ERROR, this.get(CODE_TAG));
    }

    /**
     * 方便链式调用
     *
     * @param key   键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public RestMapResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}