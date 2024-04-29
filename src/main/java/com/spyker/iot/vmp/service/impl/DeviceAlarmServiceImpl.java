package com.spyker.iot.vmp.service.impl;

import com.spyker.iot.vmp.gb28181.bean.DeviceAlarm;
import com.spyker.iot.vmp.service.IDeviceAlarmService;
import com.spyker.iot.vmp.storager.dao.DeviceAlarmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceAlarmServiceImpl implements IDeviceAlarmService {

    @Autowired private DeviceAlarmMapper deviceAlarmMapper;

    @Override
    public void add(DeviceAlarm deviceAlarm) {
        deviceAlarmMapper.add(deviceAlarm);
    }

    @Override
    public int clearAlarmBeforeTime(Integer id, List<String> deviceIdList, String time) {
        return deviceAlarmMapper.clearAlarmBeforeTime(id, deviceIdList, time);
    }
}