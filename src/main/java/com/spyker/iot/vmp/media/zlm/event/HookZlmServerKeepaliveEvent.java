package com.spyker.iot.vmp.media.zlm.event;

import com.spyker.iot.vmp.media.bean.MediaServer;

import org.springframework.context.ApplicationEvent;

/** zlm 心跳事件 */
public class HookZlmServerKeepaliveEvent extends ApplicationEvent {

    private MediaServer mediaServerItem;

    public HookZlmServerKeepaliveEvent(Object source) {
        super(source);
    }

    public MediaServer getMediaServerItem() {
        return mediaServerItem;
    }

    public void setMediaServerItem(MediaServer mediaServerItem) {
        this.mediaServerItem = mediaServerItem;
    }
}