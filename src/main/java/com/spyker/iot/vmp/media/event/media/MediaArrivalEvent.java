package com.spyker.iot.vmp.media.event.media;

import com.spyker.iot.vmp.media.bean.MediaInfo;
import com.spyker.iot.vmp.media.bean.MediaServer;
import com.spyker.iot.vmp.media.zlm.dto.hook.OnStreamChangedHookParam;

/** 流到来事件 */
public class MediaArrivalEvent extends MediaEvent {
    private MediaInfo mediaInfo;
    private String callId;

    public MediaArrivalEvent(Object source) {
        super(source);
    }

    public static MediaArrivalEvent getInstance(
            Object source, OnStreamChangedHookParam hookParam, MediaServer mediaServer) {
        MediaArrivalEvent mediaArrivalEvent = new MediaArrivalEvent(source);
        mediaArrivalEvent.setMediaInfo(MediaInfo.getInstance(hookParam, mediaServer));
        mediaArrivalEvent.setApp(hookParam.getApp());
        mediaArrivalEvent.setStream(hookParam.getStream());
        mediaArrivalEvent.setMediaServer(mediaServer);
        mediaArrivalEvent.setSchema(hookParam.getSchema());
        mediaArrivalEvent.setCallId(hookParam.getCallId());
        return mediaArrivalEvent;
    }

    public MediaInfo getMediaInfo() {
        return mediaInfo;
    }

    public void setMediaInfo(MediaInfo mediaInfo) {
        this.mediaInfo = mediaInfo;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }
}