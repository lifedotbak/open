package com.genersoft.iot.vmp.gb28181.event.alarm;

import com.genersoft.iot.vmp.gb28181.bean.DeviceAlarm;
import org.springframework.context.ApplicationEvent;

/**
 * @description: 报警事件
 * @author: lawrencehj
 * @data: 2021-01-20
 */
public class AlarmEvent extends ApplicationEvent {
    /** */
    private static final long serialVersionUID = 1L;
    private DeviceAlarm deviceAlarm;

    public AlarmEvent(Object source) {
        super(source);
    }

    public DeviceAlarm getAlarmInfo() {
        return deviceAlarm;
    }

    public void setAlarmInfo(DeviceAlarm deviceAlarm) {
        this.deviceAlarm = deviceAlarm;
    }
}