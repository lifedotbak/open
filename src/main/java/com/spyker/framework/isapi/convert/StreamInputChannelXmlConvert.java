package com.spyker.framework.isapi.convert;

public class StreamInputChannelXmlConvert {

    String name;
    String group;
    String ip;

    /** 端口，默认8000 */
    String port = "8000";

    String userName;
    String password;

    public StreamInputChannelXmlConvert(
            String name, String group, String ip, String port, String userName, String password) {
        this.name = name;
        this.group = group;
        this.ip = ip;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public String getXml() {
        String input =
                "<?xml version: \"1.0\" encoding=\"utf-8\"?><StreamInputChannel xmlns=\"http://www.isapi"
                        + ".org/ver20/XMLSchema\" version=\"2.0\"><id/><name>"
                        + name
                        + "</name><group>"
                        + group
                        + "</group"
                        + "><StreamInput"
                        + "><streamInputMode>realtime</streamInputMode><StreamInputRealtime><StreamRealtimeUnitList"
                        + "><StreamRealtimeUnit><streamType>by "
                        + "domain</streamType><StreamByDomain><EncodeDevInfo><domain>"
                        + ip
                        + "</domain><port>"
                        + port
                        + "</port"
                        + "><transmitProtocol>tcp</transmitProtocol><protocol>HIKVISION</protocol><username>"
                        + userName
                        + "</username><password>"
                        + password
                        + "</password><channelMode>normal</channelMode><channelNormal>1"
                        + "</channelNormal><channelType>main</channelType></EncodeDevInfo><MediaGatewayInfo><enabled>false</enabled><domain/><port/><transmitProtocol>tcp</transmitProtocol></MediaGatewayInfo></StreamByDomain></StreamRealtimeUnit></StreamRealtimeUnitList></StreamInputRealtime><streamEncryptEnable>false</streamEncryptEnable></StreamInput></StreamInputChannel>";

        return input;
    }
}