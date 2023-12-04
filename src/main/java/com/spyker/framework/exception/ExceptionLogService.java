package com.spyker.framework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

public interface ExceptionLogService {

    /**
     * 日志处理
     *
     * @param exceptionLog
     */
    void doLog(ExceptionLog exceptionLog);
}