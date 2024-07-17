package com.spyker.commons.handler;

import com.spyker.commons.entity.SysExceptionLog;
import com.spyker.commons.service.SysExceptionLogService;
import com.spyker.framework.exception.entity.ExceptionLog;
import com.spyker.framework.exception.handler.ExceptionLogHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbExceptionLogHandler implements ExceptionLogHandler {

    @Autowired private SysExceptionLogService sysExceptionLogService;

    @Override
    public void handler(ExceptionLog exceptionLog) {

        SysExceptionLog sysExceptionLog = new SysExceptionLog();

        sysExceptionLog
                .setExpController(exceptionLog.getExpController())
                .setExpDetail(exceptionLog.getExpDetail())
                .setExpMethod(exceptionLog.getExpMethod())
                .setExpParams(exceptionLog.getExpParams())
                .setExpType(exceptionLog.getExpType())
                .setExpUrl(exceptionLog.getExpUrl());

        sysExceptionLogService.save(sysExceptionLog);
    }
}