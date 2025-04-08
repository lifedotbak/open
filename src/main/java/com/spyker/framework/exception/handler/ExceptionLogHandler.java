package com.spyker.framework.exception.handler;

import com.spyker.framework.exception.entity.ExceptionLog;

public interface ExceptionLogHandler {

    /**
     * 日志处理
     *
     * @param exceptionLog
     */
    void handler(ExceptionLog exceptionLog);
}
