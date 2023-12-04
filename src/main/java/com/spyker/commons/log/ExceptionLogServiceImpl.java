package com.spyker.commons.log;

import com.spyker.framework.exception.ExceptionLog;
import com.spyker.framework.exception.ExceptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExceptionLogServiceImpl implements ExceptionLogService {

    public void doLog(ExceptionLog exceptionLog) {

        log.error("exceptionLog -->{}", exceptionLog);
    }
}