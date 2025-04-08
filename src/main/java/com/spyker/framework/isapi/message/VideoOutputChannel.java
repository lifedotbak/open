package com.spyker.framework.isapi.message;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.Data;

@Data
public class VideoOutputChannel {

    @XStreamAsAttribute String version;
    @XStreamAsAttribute String xmlns;

    String id;
    String portType;
    String deviceID;
    String useEDIDResolution;
    String outputMode;
    String name;

    /** outputPortEnabled 是否启用输出口 */
    String outputPortEnabled;

    String LEDSendCardResolutionEnabled;
    String outputPortAccessStatus;

    OutputResolution OutputResolution;

    PortInBoard PortInBoard;
}
