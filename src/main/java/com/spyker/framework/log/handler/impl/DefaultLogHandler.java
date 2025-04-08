package com.spyker.framework.log.handler.impl;

import com.spyker.framework.log.entity.OperationLog;
import com.spyker.framework.log.handler.LogHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultLogHandler implements LogHandler {

    @Override
    public void handler(OperationLog exceptionLog) {

        log.error("defaultLogHandler -->{}", exceptionLog);
    }
}
