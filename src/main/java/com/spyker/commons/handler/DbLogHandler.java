package com.spyker.commons.handler;

import com.spyker.commons.entity.SysOperLog;
import com.spyker.commons.service.SysOperLogService;
import com.spyker.framework.log.entity.OperationLog;
import com.spyker.framework.log.handler.LogHandler;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbLogHandler implements LogHandler {

    @Autowired private SysOperLogService sysOperLogService;

    @Override
    public void handler(OperationLog operationLog) {

        SysOperLog sysExceptionLog = new SysOperLog();

        BeanUtils.copyProperties(operationLog, sysExceptionLog);

        sysOperLogService.save(sysExceptionLog);
    }
}
