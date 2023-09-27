package com.spyker.framework.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spyker.application.enums.SuccessCode;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class RestResponse<T> {

    private int code;
    private String msg;
    private T result;

    public static <T> RestResponse<T> success(T result) {

        RestResponse<T> response = new RestResponse<>(SuccessCode.OK.code, SuccessCode.OK.msg, result);

        return response;
    }

    public static <T> RestResponse<T> success() {
        return new RestResponse<>(SuccessCode.OK.code, SuccessCode.OK.msg);
    }

    public static <T> RestResponse<T> error(RestCode code) {
        return new RestResponse<>(code.getCode(), code.getMsg());
    }

    public static <T> RestResponse<T> error(Integer code, String msg) {
        return new RestResponse<>(code, msg);
    }

    private RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private RestResponse(int code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

}