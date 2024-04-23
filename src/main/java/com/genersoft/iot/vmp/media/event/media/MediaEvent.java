package com.genersoft.iot.vmp.media.event.media;

import com.genersoft.iot.vmp.media.bean.MediaServer;

import org.springframework.context.ApplicationEvent;

/** 流到来事件 */
public class MediaEvent extends ApplicationEvent {

    private String app;
    private String stream;
    private MediaServer mediaServer;
    private String schema;

    public MediaEvent(Object source) {
        super(source);
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

    public MediaServer getMediaServer() {
        return mediaServer;
    }

    public void setMediaServer(MediaServer mediaServer) {
        this.mediaServer = mediaServer;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}