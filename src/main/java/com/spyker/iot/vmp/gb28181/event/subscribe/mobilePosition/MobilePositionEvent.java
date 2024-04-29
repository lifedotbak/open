package com.spyker.iot.vmp.gb28181.event.subscribe.mobilePosition;

import com.spyker.iot.vmp.gb28181.bean.MobilePosition;

import org.springframework.context.ApplicationEvent;

public class MobilePositionEvent extends ApplicationEvent {
    private MobilePosition mobilePosition;

    public MobilePositionEvent(Object source) {
        super(source);
    }

    public MobilePosition getMobilePosition() {
        return mobilePosition;
    }

    public void setMobilePosition(MobilePosition mobilePosition) {
        this.mobilePosition = mobilePosition;
    }
}