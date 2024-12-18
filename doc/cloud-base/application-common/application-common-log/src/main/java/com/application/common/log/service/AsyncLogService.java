package com.application.common.log.service;

import com.application.system.api.domain.SysOperLog;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 *
 * @author ruoyi
 */
@Service
public class AsyncLogService
{
//    @Autowired
//    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog) throws Exception
    {
//        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}