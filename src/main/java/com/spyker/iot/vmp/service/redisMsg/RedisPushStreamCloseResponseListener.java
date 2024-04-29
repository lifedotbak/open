package com.spyker.iot.vmp.service.redisMsg;

import com.alibaba.fastjson2.JSON;
import com.spyker.iot.vmp.conf.UserSetting;
import com.spyker.iot.vmp.gb28181.bean.InviteStreamType;
import com.spyker.iot.vmp.gb28181.bean.ParentPlatform;
import com.spyker.iot.vmp.gb28181.bean.SendRtpItem;
import com.spyker.iot.vmp.gb28181.transmit.cmd.ISIPCommanderForPlatform;
import com.spyker.iot.vmp.media.bean.MediaServer;
import com.spyker.iot.vmp.media.service.IMediaServerService;
import com.spyker.iot.vmp.media.zlm.dto.StreamPushItem;
import com.spyker.iot.vmp.service.IStreamPushService;
import com.spyker.iot.vmp.service.bean.MessageForPushChannel;
import com.spyker.iot.vmp.service.bean.MessageForPushChannelResponse;
import com.spyker.iot.vmp.storager.IRedisCatchStorage;
import com.spyker.iot.vmp.storager.IVideoManagerStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;

/**
 * 接收redis发送的结束推流请求
 *
 * @author lin
 */
@Component
public class RedisPushStreamCloseResponseListener implements MessageListener {

    private static final Logger logger =
            LoggerFactory.getLogger(RedisPushStreamCloseResponseListener.class);
    private final Map<String, PushStreamResponseEvent> responseEvents = new ConcurrentHashMap<>();
    @Autowired private IStreamPushService streamPushService;
    @Autowired private IRedisCatchStorage redisCatchStorage;
    @Autowired private IVideoManagerStorage storager;
    @Autowired private ISIPCommanderForPlatform commanderFroPlatform;
    @Autowired private UserSetting userSetting;
    @Autowired private IMediaServerService mediaServerService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        logger.info("[REDIS消息-推流结束]： {}", new String(message.getBody()));
        MessageForPushChannel pushChannel =
                JSON.parseObject(message.getBody(), MessageForPushChannel.class);
        StreamPushItem push =
                streamPushService.getPush(pushChannel.getApp(), pushChannel.getStream());
        if (push != null) {
            List<SendRtpItem> sendRtpItems =
                    redisCatchStorage.querySendRTPServerByChannelId(push.getGbId());
            if (!sendRtpItems.isEmpty()) {
                for (SendRtpItem sendRtpItem : sendRtpItems) {
                    ParentPlatform parentPlatform =
                            storager.queryParentPlatByServerGBId(sendRtpItem.getPlatformId());
                    if (parentPlatform != null) {
                        redisCatchStorage.deleteSendRTPServer(
                                sendRtpItem.getPlatformId(),
                                sendRtpItem.getChannelId(),
                                sendRtpItem.getCallId(),
                                sendRtpItem.getStream());
                        try {
                            commanderFroPlatform.streamByeCmd(parentPlatform, sendRtpItem);
                        } catch (SipException | InvalidArgumentException | ParseException e) {
                            logger.error("[命令发送失败] 国标级联 发送BYE: {}", e.getMessage());
                        }
                    }
                    if (push.isSelf()) {
                        // 停止向上级推流
                        logger.info("[REDIS消息-推流结束] 停止向上级推流：{}", sendRtpItem.getStream());
                        MediaServer mediaInfo =
                                mediaServerService.getOne(sendRtpItem.getMediaServerId());
                        redisCatchStorage.deleteSendRTPServer(
                                sendRtpItem.getPlatformId(),
                                sendRtpItem.getChannelId(),
                                sendRtpItem.getCallId(),
                                sendRtpItem.getStream());
                        mediaServerService.stopSendRtp(
                                mediaInfo,
                                sendRtpItem.getApp(),
                                sendRtpItem.getStream(),
                                sendRtpItem.getSsrc());
                        if (InviteStreamType.PUSH == sendRtpItem.getPlayType()) {
                            MessageForPushChannel messageForPushChannel =
                                    MessageForPushChannel.getInstance(
                                            0,
                                            sendRtpItem.getApp(),
                                            sendRtpItem.getStream(),
                                            sendRtpItem.getChannelId(),
                                            sendRtpItem.getPlatformId(),
                                            parentPlatform.getName(),
                                            userSetting.getServerId(),
                                            sendRtpItem.getMediaServerId());
                            messageForPushChannel.setPlatFormIndex(parentPlatform.getId());
                            redisCatchStorage.sendPlatformStopPlayMsg(messageForPushChannel);
                        }
                    }
                }
            }
        }
    }

    public void addEvent(String app, String stream, PushStreamResponseEvent callback) {
        responseEvents.put(app + stream, callback);
    }

    public void removeEvent(String app, String stream) {
        responseEvents.remove(app + stream);
    }

    public interface PushStreamResponseEvent {
        void run(MessageForPushChannelResponse response);
    }
}