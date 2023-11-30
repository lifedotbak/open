package com.spyker.framework.result;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @ClassName ResultCodeEnum
 * @Description 公共模块响应码枚举
 * @Author HZW
 * @Date 2023/2/21 16:10
 * @Version 1.0
 */
public enum CommonResultCode {

    /*** 通用部分 100 - 599***/
    // 请求成功
    SUCCESS(200, "请求成功"),
    // 重定向
    REDIRECT(301, "redirect"),
    VALIDATE_FAILED(400, "参数检验失败"),
    UNAUTHORIZED(401, "未登录，请登录！"),
    PERMISSION_EXPIRATION(402, "token过期，请登录！"),
    FORBIDDEN(403, "无访问权限,请联系管理员授予权限"),
    // 资源未找到
    NOT_FOUND(404, "资源未找到"),
    // 服务器错误
    ERROR(500, "系统异常，请稍后重试"),
    ;

    /**
     * 响应状态码
     */
    private Integer  code;
    /**
     * 响应信息
     */
    private String   message;
    /**
     * 响应信息补充
     */
    private Object[] msgParams;

    CommonResultCode(Integer code, String message) {
        this.code    = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public CommonResultCode setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        if (ArrayUtil.isNotEmpty(msgParams)) {
            return StrUtil.format(message, msgParams);
        }
        return message;
    }

    public CommonResultCode setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object[] getMsgParams() {
        return msgParams;
    }

    public void setMsgParams(Object... msgParams) {
        this.msgParams = msgParams;
    }
}