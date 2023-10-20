package com.spyker.framework.third.zlmediakit.model;

import lombok.Data;

@Data
public class OpResult {

    /**
     * Exception = -400,//代码抛异常
     * InvalidArgs = -300,//参数不合法
     * SqlFailed = -200,//sql执行失败
     * AuthFailed = -100,//鉴权失败
     * OtherFailed = -1,//业务代码执行失败，
     * Success = 0//执行成功
     */
    int code;
    String message;
    String result;

    /**
     * 筛选命中的流个数
     */
    int count_hit;
    /**
     * 被关闭的流个数，可能小于count_hit
     */
    int count_closed;

    OpResultData data;

    @Data
    public class OpResultData {

        /**
         * 流的唯一标识
         */
        String key;

        String flag;
    }
}