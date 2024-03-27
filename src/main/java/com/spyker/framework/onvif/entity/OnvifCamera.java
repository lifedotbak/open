package com.spyker.framework.onvif.entity;

import lombok.Data;

@Data
public class OnvifCamera {
    /***
     * 类型
     *1-gb28181
     *2-onvif
     *3-rtsp
     */
    Integer type;

    /***
     * 设备id
     */
    String deviceId;

    /***
     * 通道id
     */
    String channelId;

    /***
     * 1-在线
     * 0-离线
     */
    Integer status;

    /***
     * 录制状态
     * 0-未录制
     * 1-录制中
     */
    Integer recordStatus;

    /***
     * 直播状态
     * 1-直播中
     * 0-未直播
     */
    Integer liveStatus;

    /***
     * 直播key
     */
    String liveKey;

    /***
     * 品牌id
     */
    Long brandId;

    /***
     * ip地址
     */
    String ip;

    /***
     * rtsp 通道
     */
    String rtspChannel;

    /***
     * rtsp port
     */
    Integer rtspPort;

    /***
     * rtsp使用
     */
    String username;

    /***
     * rtsp使用
     */
    String password;

    String onvifUsername;
    String onvifPassword;
    Integer onvifPort;
    private Long id;
}