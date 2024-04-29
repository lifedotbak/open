package com.spyker.iot.vmp.storager;

import com.spyker.iot.vmp.gb28181.bean.*;
import com.spyker.iot.vmp.media.zlm.dto.StreamProxyItem;
import com.spyker.iot.vmp.service.bean.GPSMsgInfo;
import com.spyker.iot.vmp.storager.dao.dto.ChannelSourceInfo;
import com.spyker.iot.vmp.vmanager.gb28181.platform.bean.ChannelReduce;
import com.spyker.iot.vmp.web.gb28181.dto.DeviceChannelExtend;

import java.util.List;

/**
 * @description:视频设备数据存储接口
 * @author: swwheihei
 * @date: 2020年5月6日 下午2:14:31
 */
@SuppressWarnings("rawtypes")
public interface IVideoManagerStorage {

    /**
     * 根据设备ID判断设备是否存在
     *
     * @param deviceId 设备ID
     * @return true:存在 false：不存在
     */
    boolean exists(String deviceId);

    /**
     * 开始播放
     *
     * @param deviceId 设备id
     * @param channelId 通道ID
     * @param streamId 流地址
     */
    void startPlay(String deviceId, String channelId, String streamId);

    /**
     * 停止播放
     *
     * @param deviceId 设备id
     * @param channelId 通道ID
     */
    void stopPlay(String deviceId, String channelId);

    /**
     * 获取设备
     *
     * @param deviceId 设备ID
     * @return DShadow 设备对象
     */
    Device queryVideoDevice(String deviceId);

    List<DeviceChannelExtend> queryChannelsByDeviceIdWithStartAndLimit(
            String deviceId,
            List<String> channelIds,
            String query,
            Boolean hasSubChannel,
            Boolean online,
            int start,
            int limit);

    /**
     * 获取某个设备的通道列表
     *
     * @param deviceId 设备ID
     * @return
     */
    List<DeviceChannel> queryChannelsByDeviceId(
            String deviceId, Boolean online, List<String> channelIds);

    List<DeviceChannel> queryOnlineChannelsByDeviceId(String deviceId);

    /**
     * 获取某个设备的通道
     *
     * @param deviceId 设备ID
     * @param channelId 通道ID
     */
    DeviceChannel queryChannel(String deviceId, String channelId);

    /**
     * 删除通道
     *
     * @param deviceId 设备ID
     * @param channelId 通道ID
     */
    int delChannel(String deviceId, String channelId);

    /**
     * 获取多个设备
     *
     * @return List<Device> 设备对象数组
     */
    List<Device> queryVideoDeviceList(Boolean online);

    /**
     * 清空通道
     *
     * @param deviceId
     */
    void cleanChannelsForDevice(String deviceId);

    /**
     * 更新上级平台
     *
     * @param parentPlatform
     */
    boolean updateParentPlatform(ParentPlatform parentPlatform);

    /**
     * 添加上级平台
     *
     * @param parentPlatform
     */
    boolean addParentPlatform(ParentPlatform parentPlatform);

    /**
     * 删除上级平台
     *
     * @param parentPlatform
     */
    boolean deleteParentPlatform(ParentPlatform parentPlatform);

    /**
     * 获取所有已启用的平台
     *
     * @return
     */
    List<ParentPlatform> queryEnableParentPlatformList(boolean enable);

    /**
     * 获取上级平台
     *
     * @param platformGbId
     * @return
     */
    ParentPlatform queryParentPlatByServerGBId(String platformGbId);

    /** 所有平台离线 */
    void outlineForAllParentPlatform();

    /** 查询设备的通道信息 */
    List<DeviceChannelInPlatform> queryChannelListInParentPlatform(String platformId);

    /**
     * 移除上级平台的通道信息
     *
     * @param platformId
     * @param channelReduces
     * @return
     */
    int delChannelForGB(String platformId, List<ChannelReduce> channelReduces);

    DeviceChannel queryChannelInParentPlatform(String platformId, String channelId);

    List<PlatformCatalog> queryChannelInParentPlatformAndCatalog(
            String platformId, String catalogId);

    List<PlatformCatalog> queryStreamInParentPlatformAndCatalog(
            String platformId, String catalogId);

    Device queryVideoDeviceByPlatformIdAndChannelId(String platformId, String channelId);

    /**
     * 针对deviceinfo指令的查询接口
     *
     * @param platformId 平台id
     * @param channelId 通道id
     * @return 设备信息
     */
    Device queryDeviceInfoByPlatformIdAndChannelId(String platformId, String channelId);

    /**
     * 添加Mobile Position设备移动位置
     *
     * @param mobilePosition
     * @return
     */
    boolean insertMobilePosition(MobilePosition mobilePosition);

    /**
     * 查询移动位置轨迹
     *
     * @param deviceId
     * @param startTime
     * @param endTime
     */
    List<MobilePosition> queryMobilePositions(
            String deviceId, String channelId, String startTime, String endTime);

    /**
     * 查询最新移动位置
     *
     * @param deviceId
     */
    MobilePosition queryLatestPosition(String deviceId);

    /**
     * 删除指定设备的所有移动位置
     *
     * @param deviceId
     */
    int clearMobilePositionsByDeviceId(String deviceId);

    /**
     * 移除代理流
     *
     * @param app
     * @param stream
     * @return
     */
    int deleteStreamProxy(String app, String stream);

    /**
     * 按照是否启用获取代理流
     *
     * @param enable
     * @return
     */
    List<StreamProxyItem> getStreamProxyListForEnable(boolean enable);

    /**
     * 按照是app和stream获取代理流
     *
     * @param app
     * @param stream
     * @return
     */
    StreamProxyItem queryStreamProxy(String app, String stream);

    /**
     * 根据国标ID获取平台关联的直播流
     *
     * @param platformId
     * @param channelId
     * @return
     */
    GbStream queryStreamInParentPlatform(String platformId, String channelId);

    /**
     * 获取平台关联的直播流
     *
     * @param platformId
     * @return
     */
    List<DeviceChannel> queryGbStreamListInPlatform(String platformId);

    /**
     * 移除单个推流
     *
     * @param app
     * @param stream
     */
    int removeMedia(String app, String stream);

    /** 设置流离线 */
    int mediaOffline(String app, String streamId);

    /** 设置流上线 */
    int mediaOnline(String app, String streamId);

    /** 设置平台在线/离线 */
    void updateParentPlatformStatus(String platformGbID, boolean online);

    /**
     * 根据媒体ID获取启用/不启用的代理列表
     *
     * @param id 媒体ID
     * @param enable 启用/不启用
     * @return
     */
    List<StreamProxyItem> getStreamProxyListForEnableInMediaServer(String id, boolean enable);

    /**
     * 根据通道ID获取其所在设备
     *
     * @param channelId 通道ID
     * @return
     */
    Device queryVideoDeviceByChannelId(String channelId);

    /**
     * 通道上线
     *
     * @param channelId 通道ID
     */
    void deviceChannelOnline(String deviceId, String channelId);

    /**
     * 通道离线
     *
     * @param channelId 通道ID
     */
    void deviceChannelOffline(String deviceId, String channelId);

    /**
     * 通过app与stream获取StreamProxy
     *
     * @param app
     * @param streamId
     * @return
     */
    StreamProxyItem getStreamProxyByAppAndStream(String app, String streamId);

    /**
     * catlog查询结束后完全重写通道信息
     *
     * @param deviceId
     * @param deviceChannelList
     */
    boolean resetChannels(String deviceId, List<DeviceChannel> deviceChannelList);

    boolean updateChannels(String deviceId, List<DeviceChannel> deviceChannelList);

    /**
     * 获取目录信息
     *
     * @param platformId
     * @param parentId
     * @return
     */
    List<PlatformCatalog> getChildrenCatalogByPlatform(String platformId, String parentId);

    int addCatalog(PlatformCatalog platformCatalog);

    PlatformCatalog getCatalog(String platformId, String id);

    int delCatalog(String platformId, String id);

    int updateCatalog(PlatformCatalog platformCatalog);

    int setDefaultCatalog(String platformId, String catalogId);

    List<DeviceChannel> queryCatalogInPlatform(String serverGBId);

    int delRelation(PlatformCatalog platformCatalog);

    int updateStreamGPS(List<GPSMsgInfo> gpsMsgInfo);

    List<ParentPlatform> queryPlatFormListForGBWithGBId(String channelId, List<String> platforms);

    List<ParentPlatform> queryPlatFormListForStreamWithGBId(
            String app, String stream, List<String> platforms);

    GbStream getGbStream(String app, String streamId);

    void delCatalogByPlatformId(String serverGBId);

    void delRelationByPlatformId(String serverGBId);

    PlatformCatalog queryDefaultCatalogInPlatform(String platformId);

    List<ChannelSourceInfo> getChannelSource(String platformId, String gbId);

    void updateChannelPosition(DeviceChannel deviceChannel);

    void cleanContentForPlatform(String serverGBId);

    List<DeviceChannel> queryChannelWithCatalog(String serverGBId);

    List<DeviceChannelExtend> queryChannelsByDeviceId(
            String serial, List<String> channelIds, Boolean online);

    List<ParentPlatform> queryEnablePlatformListWithAsMessageChannel();

    List<Device> queryDeviceWithAsMessageChannel();
}