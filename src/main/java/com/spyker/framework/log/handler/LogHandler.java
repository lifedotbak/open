package com.spyker.framework.log.handler;

import com.spyker.framework.log.entity.OperationLog;

public interface LogHandler {

    /**
     * 日志处理
     *
     * @param exceptionLog
     */
    void handler(OperationLog exceptionLog);
}
