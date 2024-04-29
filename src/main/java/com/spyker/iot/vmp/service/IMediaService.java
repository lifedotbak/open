package com.spyker.iot.vmp.service;

import com.spyker.iot.vmp.media.bean.MediaServer;
import com.spyker.iot.vmp.media.bean.ResultForOnPublish;

/** 媒体信息业务 */
public interface IMediaService {

    /** 播放鉴权 */
    boolean authenticatePlay(String app, String stream, String callId);

    ResultForOnPublish authenticatePublish(
            MediaServer mediaServer, String app, String stream, String params);

    boolean closeStreamOnNoneReader(String mediaServerId, String app, String stream, String schema);
}