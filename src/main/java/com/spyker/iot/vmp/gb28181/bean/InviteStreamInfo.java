package com.spyker.iot.vmp.gb28181.bean;

import com.alibaba.fastjson2.JSONObject;
import com.spyker.iot.vmp.media.bean.MediaServer;

public class InviteStreamInfo {

    private MediaServer mediaServerItem;
    private JSONObject response;
    private String callId;
    private String app;
    private String stream;

    public InviteStreamInfo(
            MediaServer mediaServerItem,
            JSONObject response,
            String callId,
            String app,
            String stream) {
        this.mediaServerItem = mediaServerItem;
        this.response = response;
        this.callId = callId;
        this.app = app;
        this.stream = stream;
    }

    public MediaServer getMediaServerItem() {
        return mediaServerItem;
    }

    public void setMediaServerItem(MediaServer mediaServerItem) {
        this.mediaServerItem = mediaServerItem;
    }

    public JSONObject getResponse() {
        return response;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
}