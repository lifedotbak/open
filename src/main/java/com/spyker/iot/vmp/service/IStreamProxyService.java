package com.spyker.iot.vmp.service;

import com.spyker.iot.vmp.common.GeneralCallback;
import com.spyker.iot.vmp.common.StreamInfo;
import com.spyker.iot.vmp.media.bean.MediaServer;
import com.spyker.iot.vmp.media.zlm.dto.StreamProxyItem;
import com.spyker.iot.vmp.vmanager.bean.ResourceBaseInfo;
import com.spyker.iot.vmp.vmanager.bean.WVPResult;

import java.util.Map;

public interface IStreamProxyService {

    /**
     * 保存视频代理
     *
     * @param param
     */
    void save(StreamProxyItem param, GeneralCallback<StreamInfo> callback);

    /**
     * 添加视频代理到zlm
     *
     * @param param
     * @return
     */
    WVPResult<String> addStreamProxyToZlm(StreamProxyItem param);

    /**
     * 从zlm移除视频代理
     *
     * @param param
     * @return
     */
    Boolean removeStreamProxyFromZlm(StreamProxyItem param);

    /**
     * 删除视频代理
     *
     * @param app
     * @param stream
     */
    void del(String app, String stream);

    /**
     * 启用视频代理
     *
     * @param app
     * @param stream
     * @return
     */
    boolean start(String app, String stream);

    /**
     * 更新状态
     *
     * @param status 状态
     * @param app
     * @param stream
     */
    int updateStatus(boolean status, String app, String stream);

    /**
     * 停用用视频代理
     *
     * @param app
     * @param stream
     * @return
     */
    boolean stop(String app, String stream);

    /**
     * 获取ffmpeg.cmd模板
     *
     * @return
     */
    Map<String, String> getFFmpegCMDs(MediaServer mediaServerItem);

    /**
     * 根据app与stream获取streamProxy
     *
     * @return
     */
    StreamProxyItem getStreamProxyByAppAndStream(String app, String streamId);

    /**
     * 新的节点加入
     *
     * @param mediaServerId
     * @return
     */
    void zlmServerOnline(String mediaServerId);

    /**
     * 节点离线
     *
     * @param mediaServerId
     * @return
     */
    void zlmServerOffline(String mediaServerId);

    void clean();

    /** 更新代理流 */
    boolean updateStreamProxy(StreamProxyItem streamProxyItem);

    /**
     * 获取统计信息
     *
     * @return
     */
    ResourceBaseInfo getOverview();
}