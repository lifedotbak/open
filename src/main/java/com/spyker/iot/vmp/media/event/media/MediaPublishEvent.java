package com.spyker.iot.vmp.media.event.media;

import com.spyker.iot.vmp.media.bean.MediaServer;
import com.spyker.iot.vmp.media.zlm.dto.hook.OnPublishHookParam;

/** 推流鉴权事件 */
public class MediaPublishEvent extends MediaEvent {
    private String params;

    public MediaPublishEvent(Object source) {
        super(source);
    }

    public static MediaPublishEvent getInstance(
            Object source, OnPublishHookParam hookParam, MediaServer mediaServer) {
        MediaPublishEvent mediaPublishEvent = new MediaPublishEvent(source);
        mediaPublishEvent.setApp(hookParam.getApp());
        mediaPublishEvent.setStream(hookParam.getStream());
        mediaPublishEvent.setMediaServer(mediaServer);
        mediaPublishEvent.setSchema(hookParam.getSchema());
        mediaPublishEvent.setParams(hookParam.getParams());
        return mediaPublishEvent;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}