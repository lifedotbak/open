package com.genersoft.iot.vmp.service;

import com.alibaba.fastjson2.JSONArray;
import com.genersoft.iot.vmp.media.bean.MediaServer;
import com.genersoft.iot.vmp.service.bean.DownloadFileInfo;

import java.util.List;

/**
 * 云端录像管理
 *
 * @author lin
 */
public interface ICloudRecordService {

    /** 获取所有的日期 */
    List<String> getDateList(
            String app, String stream, int year, int month, List<MediaServer> mediaServerItems);

    /** 添加合并任务 */
    String addTask(
            String app,
            String stream,
            MediaServer mediaServerItem,
            String startTime,
            String endTime,
            String callId,
            String remoteHost,
            boolean filterMediaServer);

    /** 查询合并任务列表 */
    JSONArray queryTask(
            String app,
            String stream,
            String callId,
            String taskId,
            String mediaServerId,
            Boolean isEnd,
            String scheme);

    /** 收藏视频，收藏的视频过期不会删除 */
    int changeCollect(
            boolean result,
            String app,
            String stream,
            String mediaServerId,
            String startTime,
            String endTime,
            String callId);

    /** 添加指定录像收藏 */
    int changeCollectById(Integer recordId, boolean result);

    /** 获取播放地址 */
    DownloadFileInfo getPlayUrlPath(Integer recordId);
}