package com.genersoft.iot.vmp.media.zlm.event;

import com.genersoft.iot.vmp.media.bean.MediaServer;

import org.springframework.context.ApplicationEvent;

/** zlm server_start事件 */
public class HookZlmServerStartEvent extends ApplicationEvent {

    private MediaServer mediaServerItem;

    public HookZlmServerStartEvent(Object source) {
        super(source);
    }

    public MediaServer getMediaServerItem() {
        return mediaServerItem;
    }

    public void setMediaServerItem(MediaServer mediaServerItem) {
        this.mediaServerItem = mediaServerItem;
    }
}