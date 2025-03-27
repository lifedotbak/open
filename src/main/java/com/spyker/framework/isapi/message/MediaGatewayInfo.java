package com.spyker.framework.isapi.message;

import lombok.Data;

@Data
public class MediaGatewayInfo {

    String enabled;
    String domain;
    String port;
    String transmitProtocol;
}