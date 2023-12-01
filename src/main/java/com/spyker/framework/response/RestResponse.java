package com.spyker.framework.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class RestResponse<T> {

    private int code;
    private String message;
    private T result;

    private RestResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private RestResponse(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> RestResponse<T> success(T result) {

        RestResponse<T> response = new RestResponse<>(200, "success", result);

        return response;
    }

    public static <T> RestResponse<T> success() {
        return new RestResponse<>(200, "success");
    }

    public static <T> RestResponse<T> error(IResponseCode responseCode) {
        return new RestResponse<>(responseCode.getCode(), responseCode.getMessage());
    }

    public static <T> RestResponse<T> error(Integer code, String msg) {
        return new RestResponse<>(code, msg);
    }

}