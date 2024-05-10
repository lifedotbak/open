package com.spyker.framework.onvif.ver10.entity;


/**
 * java 17 record 记录类
 *
 * @param ip
 * @param onvifPort
 * @param onvifUserName
 * @param onvifPassword
 */
public record OnvifDeviceExtend(
        String ip, int onvifPort, String onvifUserName, String onvifPassword) {

    /**
     * @param onvifUserName
     * @param onvifPassword
     * @param ip
     * @param onvifPort
     */
    public OnvifDeviceExtend {}
}