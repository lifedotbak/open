package com.spyker.iot.vmp.service.impl;

import com.spyker.iot.vmp.service.ILogService;
import com.spyker.iot.vmp.storager.dao.LogMapper;
import com.spyker.iot.vmp.storager.dao.dto.LogDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired private LogMapper logMapper;

    @Override
    public void add(LogDto logDto) {
        logMapper.add(logDto);
    }

    @Override
    public int clear() {
        return logMapper.clear();
    }
}