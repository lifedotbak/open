package com.spyker.iot.vmp.service.bean;

import com.spyker.iot.vmp.gb28181.bean.SendRtpItem;
import com.spyker.iot.vmp.media.bean.MediaServer;

/**
 * redis消息：下级回复推送信息
 *
 * @author lin
 */
public class ResponseSendItemMsg {

    private SendRtpItem sendRtpItem;

    private MediaServer mediaServerItem;

    public SendRtpItem getSendRtpItem() {
        return sendRtpItem;
    }

    public void setSendRtpItem(SendRtpItem sendRtpItem) {
        this.sendRtpItem = sendRtpItem;
    }

    public MediaServer getMediaServerItem() {
        return mediaServerItem;
    }

    public void setMediaServerItem(MediaServer mediaServerItem) {
        this.mediaServerItem = mediaServerItem;
    }
}