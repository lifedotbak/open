package com.spyker.iot.vmp.vmanager.bean;

import com.spyker.iot.vmp.common.VersionPo;
import com.spyker.iot.vmp.conf.SipConfig;
import com.spyker.iot.vmp.conf.UserSetting;

public class SystemConfigInfo {

    private int serverPort;
    private SipConfig sip;
    private UserSetting addOn;
    private VersionPo version;

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public SipConfig getSip() {
        return sip;
    }

    public void setSip(SipConfig sip) {
        this.sip = sip;
    }

    public UserSetting getAddOn() {
        return addOn;
    }

    public void setAddOn(UserSetting addOn) {
        this.addOn = addOn;
    }

    public VersionPo getVersion() {
        return version;
    }

    public void setVersion(VersionPo version) {
        this.version = version;
    }
}