package com.spyker.iot.vmp.service;

import com.spyker.iot.vmp.storager.dao.dto.LogDto;

/** 系统日志 */
public interface ILogService {

    /**
     * 添加日志
     *
     * @param logDto 日志
     */
    void add(LogDto logDto);

    /** 清空 */
    int clear();
}