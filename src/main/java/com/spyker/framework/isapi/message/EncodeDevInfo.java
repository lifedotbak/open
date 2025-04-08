package com.spyker.framework.isapi.message;

import lombok.Data;

@Data
public class EncodeDevInfo {
    String domain;
    String port;
    String transmitProtocol;
    String protocol;
    String username;
    String channelMode;
    String channelType;
    String channelZero;
    String channelNormal;
    String channelStreaming;
}
