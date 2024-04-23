package com.genersoft.iot.vmp.service;

import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.conf.exception.ServiceException;
import com.genersoft.iot.vmp.gb28181.bean.Device;
import com.genersoft.iot.vmp.gb28181.bean.DeviceChannel;
import com.genersoft.iot.vmp.gb28181.bean.ParentPlatform;
import com.genersoft.iot.vmp.gb28181.bean.SendRtpItem;
import com.genersoft.iot.vmp.media.bean.MediaInfo;
import com.genersoft.iot.vmp.media.bean.MediaServer;
import com.genersoft.iot.vmp.service.bean.ErrorCallback;
import com.genersoft.iot.vmp.service.bean.SSRCInfo;
import com.genersoft.iot.vmp.vmanager.bean.AudioBroadcastResult;
import com.genersoft.iot.vmp.vmanager.gb28181.play.bean.AudioBroadcastEvent;

import gov.nist.javax.sip.message.SIPResponse;

import java.text.ParseException;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import javax.sip.header.CallIdHeader;

/** 点播处理 */
public interface IPlayService {

    void play(
            MediaServer mediaServerItem,
            SSRCInfo ssrcInfo,
            Device device,
            DeviceChannel channelId,
            ErrorCallback<Object> callback);

    SSRCInfo play(
            MediaServer mediaServerItem,
            String deviceId,
            String channelId,
            String ssrc,
            ErrorCallback<Object> callback);

    StreamInfo onPublishHandlerForPlay(
            MediaServer mediaServerItem, MediaInfo mediaInfo, String deviceId, String channelId);

    MediaServer getNewMediaServerItem(Device device);

    void playBack(
            String deviceId,
            String channelId,
            String startTime,
            String endTime,
            ErrorCallback<Object> callback);

    void playBack(
            MediaServer mediaServerItem,
            SSRCInfo ssrcInfo,
            String deviceId,
            String channelId,
            String startTime,
            String endTime,
            ErrorCallback<Object> callback);

    void zlmServerOffline(String mediaServerId);

    void download(
            String deviceId,
            String channelId,
            String startTime,
            String endTime,
            int downloadSpeed,
            ErrorCallback<Object> callback);

    void download(
            MediaServer mediaServerItem,
            SSRCInfo ssrcInfo,
            String deviceId,
            String channelId,
            String startTime,
            String endTime,
            int downloadSpeed,
            ErrorCallback<Object> callback);

    StreamInfo getDownLoadInfo(String deviceId, String channelId, String stream);

    void zlmServerOnline(String mediaServerId);

    AudioBroadcastResult audioBroadcast(Device device, String channelId, Boolean broadcastMode);

    boolean audioBroadcastCmd(
            Device device,
            String channelId,
            MediaServer mediaServerItem,
            String app,
            String stream,
            int timeout,
            boolean isFromPlatform,
            AudioBroadcastEvent event)
            throws InvalidArgumentException, ParseException, SipException;

    boolean audioBroadcastInUse(Device device, String channelId);

    void stopAudioBroadcast(String deviceId, String channelId);

    void pauseRtp(String streamId)
            throws ServiceException, InvalidArgumentException, ParseException, SipException;

    void resumeRtp(String streamId)
            throws ServiceException, InvalidArgumentException, ParseException, SipException;

    void startPushStream(
            SendRtpItem sendRtpItem,
            SIPResponse sipResponse,
            ParentPlatform platform,
            CallIdHeader callIdHeader);

    void startSendRtpStreamFailHand(
            SendRtpItem sendRtpItem, ParentPlatform platform, CallIdHeader callIdHeader);

    void talkCmd(
            Device device,
            String channelId,
            MediaServer mediaServerItem,
            String stream,
            AudioBroadcastEvent event);

    void stopTalk(Device device, String channelId, Boolean streamIsReady);

    void getSnap(String deviceId, String channelId, String fileName, ErrorCallback errorCallback);

    void stopPlay(Device device, String channelId);
}