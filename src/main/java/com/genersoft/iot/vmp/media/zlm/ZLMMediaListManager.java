package com.genersoft.iot.vmp.media.zlm;

import com.genersoft.iot.vmp.conf.UserSetting;
import com.genersoft.iot.vmp.gb28181.bean.GbStream;
import com.genersoft.iot.vmp.media.bean.MediaServer;
import com.genersoft.iot.vmp.media.service.IMediaServerService;
import com.genersoft.iot.vmp.media.zlm.dto.ChannelOnlineEvent;
import com.genersoft.iot.vmp.media.zlm.dto.StreamPushItem;
import com.genersoft.iot.vmp.media.zlm.dto.hook.OnStreamChangedHookParam;
import com.genersoft.iot.vmp.service.IStreamPushService;
import com.genersoft.iot.vmp.storager.IVideoManagerStorage;
import com.genersoft.iot.vmp.storager.dao.GbStreamMapper;
import com.genersoft.iot.vmp.storager.dao.StreamPushMapper;
import com.genersoft.iot.vmp.utils.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lin
 */
@Component
public class ZLMMediaListManager {

    private final Logger logger = LoggerFactory.getLogger("ZLMMediaListManager");

    @Autowired private IVideoManagerStorage storager;

    @Autowired private GbStreamMapper gbStreamMapper;

    @Autowired private IStreamPushService streamPushService;

    @Autowired private StreamPushMapper streamPushMapper;

    @Autowired private UserSetting userSetting;

    @Autowired private IMediaServerService mediaServerService;

    private final Map<String, ChannelOnlineEvent> channelOnPublishEvents = new ConcurrentHashMap<>();

    public StreamPushItem addPush(OnStreamChangedHookParam onStreamChangedHookParam) {
        StreamPushItem transform = streamPushService.transform(onStreamChangedHookParam);
        StreamPushItem pushInDb =
                streamPushService.getPush(
                        onStreamChangedHookParam.getApp(), onStreamChangedHookParam.getStream());
        transform.setPushIng(onStreamChangedHookParam.isRegist());
        transform.setUpdateTime(DateUtil.getNow());
        transform.setPushTime(DateUtil.getNow());
        transform.setSelf(userSetting.getServerId().equals(onStreamChangedHookParam.getSeverId()));
        if (pushInDb == null) {
            transform.setCreateTime(DateUtil.getNow());
            streamPushMapper.add(transform);
        } else {
            streamPushMapper.update(transform);
            gbStreamMapper.updateMediaServer(
                    onStreamChangedHookParam.getApp(),
                    onStreamChangedHookParam.getStream(),
                    onStreamChangedHookParam.getMediaServerId());
        }
        ChannelOnlineEvent channelOnlineEventLister =
                getChannelOnlineEventLister(transform.getApp(), transform.getStream());
        if (channelOnlineEventLister != null) {
            try {
                channelOnlineEventLister.run(
                        transform.getApp(), transform.getStream(), transform.getServerId());
            } catch (ParseException e) {
                logger.error("addPush: ", e);
            }
            removedChannelOnlineEventLister(transform.getApp(), transform.getStream());
        }
        return transform;
    }

    public void sendStreamEvent(String app, String stream, String mediaServerId) {
        MediaServer mediaServerItem = mediaServerService.getOne(mediaServerId);
        // 查看推流状态
        Boolean streamReady = mediaServerService.isStreamReady(mediaServerItem, app, stream);
        if (streamReady != null && streamReady) {
            ChannelOnlineEvent channelOnlineEventLister = getChannelOnlineEventLister(app, stream);
            if (channelOnlineEventLister != null) {
                try {
                    channelOnlineEventLister.run(app, stream, mediaServerId);
                } catch (ParseException e) {
                    logger.error("sendStreamEvent: ", e);
                }
                removedChannelOnlineEventLister(app, stream);
            }
        }
    }

    public int removeMedia(String app, String streamId) {
        // 查找是否关联了国标， 关联了不删除， 置为离线
        GbStream gbStream = gbStreamMapper.selectOne(app, streamId);
        int result;
        if (gbStream == null) {
            result = storager.removeMedia(app, streamId);
        } else {
            result = storager.mediaOffline(app, streamId);
        }
        return result;
    }

    public void addChannelOnlineEventLister(
            String app, String stream, ChannelOnlineEvent callback) {
        this.channelOnPublishEvents.put(app + "_" + stream, callback);
    }

    public void removedChannelOnlineEventLister(String app, String stream) {
        this.channelOnPublishEvents.remove(app + "_" + stream);
    }

    public ChannelOnlineEvent getChannelOnlineEventLister(String app, String stream) {
        return this.channelOnPublishEvents.get(app + "_" + stream);
    }
}