package com.spyker.iot.vmp.media.service;

import com.spyker.iot.vmp.common.CommonCallback;
import com.spyker.iot.vmp.common.StreamInfo;
import com.spyker.iot.vmp.gb28181.bean.ParentPlatform;
import com.spyker.iot.vmp.gb28181.bean.SendRtpItem;
import com.spyker.iot.vmp.media.bean.MediaInfo;
import com.spyker.iot.vmp.media.bean.MediaServer;
import com.spyker.iot.vmp.service.bean.MediaServerLoad;
import com.spyker.iot.vmp.service.bean.SSRCInfo;
import com.spyker.iot.vmp.vmanager.bean.WVPResult;

import java.util.List;
import java.util.Map;

/** 媒体服务节点 */
public interface IMediaServerService {

    List<MediaServer> getAllOnlineList();

    List<MediaServer> getAll();

    List<MediaServer> getAllFromDatabase();

    List<MediaServer> getAllOnline();

    MediaServer getOne(String generalMediaServerId);

    void syncCatchFromDatabase();

    MediaServer getMediaServerForMinimumLoad(Boolean hasAssist);

    void updateVmServer(List<MediaServer> mediaServerItemList);

    SSRCInfo openRTPServer(
            MediaServer mediaServerItem,
            String streamId,
            String presetSsrc,
            boolean ssrcCheck,
            boolean isPlayback,
            Integer port,
            Boolean onlyAuto,
            Boolean disableAudio,
            Boolean reUsePort,
            Integer tcpMode);

    void closeRTPServer(MediaServer mediaServerItem, String streamId);

    void closeRTPServer(
            MediaServer mediaServerItem, String streamId, CommonCallback<Boolean> callback);

    Boolean updateRtpServerSSRC(MediaServer mediaServerItem, String streamId, String ssrc);

    void closeRTPServer(String mediaServerId, String streamId);

    void clearRTPServer(MediaServer mediaServerItem);

    void update(MediaServer mediaSerItem);

    void addCount(String mediaServerId);

    void removeCount(String mediaServerId);

    void releaseSsrc(String mediaServerItemId, String ssrc);

    void clearMediaServerForOnline();

    void add(MediaServer mediaSerItem);

    void resetOnlineServerItem(MediaServer serverItem);

    MediaServer checkMediaServer(String ip, int port, String secret, String type);

    boolean checkMediaRecordServer(String ip, int port);

    void delete(String id);

    MediaServer getDefaultMediaServer();

    MediaServerLoad getLoad(MediaServer mediaServerItem);

    List<MediaServer> getAllWithAssistPort();

    MediaServer getOneFromDatabase(String id);

    boolean stopSendRtp(MediaServer mediaInfo, String app, String stream, String ssrc);

    boolean deleteRecordDirectory(
            MediaServer mediaServerItem, String app, String stream, String date, String fileName);

    List<StreamInfo> getMediaList(MediaServer mediaInfo, String app, String stream, String callId);

    Boolean connectRtpServer(MediaServer mediaServerItem, String address, int port, String stream);

    void getSnap(
            MediaServer mediaServerItemInuse,
            String streamUrl,
            int timeoutSec,
            int expireSec,
            String path,
            String fileName);

    MediaInfo getMediaInfo(MediaServer mediaServerItem, String app, String stream);

    Boolean pauseRtpCheck(MediaServer mediaServerItem, String streamKey);

    boolean resumeRtpCheck(MediaServer mediaServerItem, String streamKey);

    String getFfmpegCmd(MediaServer mediaServer, String cmdKey);

    void closeStreams(MediaServer mediaServerItem, String app, String stream);

    WVPResult<String> addFFmpegSource(
            MediaServer mediaServerItem,
            String srcUrl,
            String dstUrl,
            int timeoutMs,
            boolean enableAudio,
            boolean enableMp4,
            String ffmpegCmdKey);

    WVPResult<String> addStreamProxy(
            MediaServer mediaServerItem,
            String app,
            String stream,
            String url,
            boolean enableAudio,
            boolean enableMp4,
            String rtpType);

    Boolean delFFmpegSource(MediaServer mediaServerItem, String streamKey);

    Boolean delStreamProxy(MediaServer mediaServerItem, String streamKey);

    Map<String, String> getFFmpegCMDs(MediaServer mediaServer);

    /**
     * 根据应用名和流ID获取播放地址, 通过zlm接口检查是否存在
     *
     * @param app
     * @param stream
     * @return
     */
    StreamInfo getStreamInfoByAppAndStreamWithCheck(
            String app, String stream, String mediaServerId, String addr, boolean authority);

    /**
     * 根据应用名和流ID获取播放地址, 通过zlm接口检查是否存在, 返回的ip使用远程访问ip，适用与zlm与wvp在一台主机的情况
     *
     * @param app
     * @param stream
     * @return
     */
    StreamInfo getStreamInfoByAppAndStreamWithCheck(
            String app, String stream, String mediaServerId, boolean authority);

    /**
     * 根据应用名和流ID获取播放地址, 只是地址拼接
     *
     * @param app
     * @param stream
     * @return
     */
    StreamInfo getStreamInfoByAppAndStream(
            MediaServer mediaServerItem,
            String app,
            String stream,
            MediaInfo mediaInfo,
            String callId);

    /**
     * 根据应用名和流ID获取播放地址, 只是地址拼接，返回的ip使用远程访问ip，适用与zlm与wvp在一台主机的情况
     *
     * @param app
     * @param stream
     * @return
     */
    StreamInfo getStreamInfoByAppAndStream(
            MediaServer mediaServer,
            String app,
            String stream,
            MediaInfo mediaInfo,
            String addr,
            String callId,
            boolean isPlay);

    Boolean isStreamReady(MediaServer mediaServer, String rtp, String streamId);

    void startSendRtpPassive(
            MediaServer mediaServer,
            ParentPlatform platform,
            SendRtpItem sendRtpItem,
            Integer timeout);

    void startSendRtp(MediaServer mediaServer, ParentPlatform platform, SendRtpItem sendRtpItem);

    SendRtpItem createSendRtpItem(
            MediaServer mediaServerItem,
            String addressStr,
            int port,
            String ssrc,
            String requesterId,
            String deviceId,
            String channelId,
            boolean mediaTransmissionTCP,
            boolean rtcp);

    SendRtpItem createSendRtpItem(
            MediaServer serverItem,
            String ip,
            int port,
            String ssrc,
            String platformId,
            String app,
            String stream,
            String channelId,
            boolean tcp,
            boolean rtcp);
}