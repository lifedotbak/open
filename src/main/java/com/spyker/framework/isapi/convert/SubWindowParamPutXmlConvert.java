package com.spyker.framework.isapi.convert;

public class SubWindowParamPutXmlConvert {

    private final String subwindowId;
    private final String streamingChannelID;

    public SubWindowParamPutXmlConvert(String subwindowId, String streamingChannelID) {
        this.subwindowId = subwindowId;
        this.streamingChannelID = streamingChannelID;
    }

    public String getXml() {
        String input =
                "<SubWindow version: '2.0' xmlns='http://www.isapi.org/ver20/XMLSchema'>"
                        + "      <id>"
                        + subwindowId
                        + "</id>"
                        + "      <SubWindowParam>"
                        + "        <signalMode>stream id</signalMode>"
                        + "        <streamingChannelID>"
                        + streamingChannelID
                        + "</streamingChannelID>"
                        + "      </SubWindowParam>"
                        + "    </SubWindow>";

        return input;
    }
}
