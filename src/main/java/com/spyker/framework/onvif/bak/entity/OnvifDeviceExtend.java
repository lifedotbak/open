package com.spyker.framework.onvif.bak.entity;

public class OnvifDeviceExtend {

    private final String onvifUserName;
    private final String onvifPassword;
    private final String ip;
    private final int onvifPort;

    /**
     * @param onvifUserName
     * @param onvifPassword
     * @param ip
     * @param onvifPort
     */
    public OnvifDeviceExtend(String ip, int onvifPort, String onvifUserName, String onvifPassword) {
        this.onvifUserName = onvifUserName;
        this.onvifPassword = onvifPassword;
        this.ip = ip;
        this.onvifPort = onvifPort;
    }

    public String getOnvifUserName() {
        return onvifUserName;
    }

    public String getOnvifPassword() {
        return onvifPassword;
    }

    public String getIp() {
        return ip;
    }

    public int getOnvifPort() {
        return onvifPort;
    }
}