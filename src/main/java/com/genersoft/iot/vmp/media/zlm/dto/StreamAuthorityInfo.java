package com.genersoft.iot.vmp.media.zlm.dto;

import com.genersoft.iot.vmp.media.event.media.MediaArrivalEvent;

/**
 * 流的鉴权信息
 *
 * @author lin
 */
public class StreamAuthorityInfo {

    private String id;
    private String app;
    private String stream;

    /**
     * 产生源类型， unknown = 0, rtmp_push=1, rtsp_push=2, rtp_push=3, pull=4, ffmpeg_pull=5, mp4_vod=6,
     * device_chn=7
     */
    private int originType;

    /** 产生源类型的字符串描述 */
    private String originTypeStr;

    /** 推流时自定义的播放鉴权ID */
    private String callId;

    /** 推流的鉴权签名 */
    private String sign;

    public static StreamAuthorityInfo getInstanceByHook(String app, String stream, String id) {
        StreamAuthorityInfo streamAuthorityInfo = new StreamAuthorityInfo();
        streamAuthorityInfo.setApp(app);
        streamAuthorityInfo.setStream(stream);
        streamAuthorityInfo.setId(id);
        return streamAuthorityInfo;
    }

    public static StreamAuthorityInfo getInstanceByHook(MediaArrivalEvent event) {
        StreamAuthorityInfo streamAuthorityInfo = new StreamAuthorityInfo();
        streamAuthorityInfo.setApp(event.getApp());
        streamAuthorityInfo.setStream(event.getStream());
        streamAuthorityInfo.setId(event.getMediaServer().getId());
        if (event.getMediaInfo() != null) {
            streamAuthorityInfo.setOriginType(event.getMediaInfo().getOriginType());
        }

        return streamAuthorityInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getOriginType() {
        return originType;
    }

    public void setOriginType(int originType) {
        this.originType = originType;
    }

    public String getOriginTypeStr() {
        return originTypeStr;
    }

    public void setOriginTypeStr(String originTypeStr) {
        this.originTypeStr = originTypeStr;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}