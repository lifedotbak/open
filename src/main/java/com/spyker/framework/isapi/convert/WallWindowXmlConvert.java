package com.spyker.framework.isapi.convert;

import lombok.Data;

/** 给墙分配视频流 */
@Data
public class WallWindowXmlConvert {

    private String windowId;
    private int x;
    private int y;

    /** width height 为整个窗口的宽高，x,y为窗口左上角的坐标，width,height为窗口的宽高，subWindowId为子窗口的ID，windowMode为窗口模式， */
    private int width;

    private int height;
    private String streamingChannelID;
    private String subWindowId;

    /** subType:int, [1#1,4#4,6#6,8#8,9#9,16#16,25#25]-->1 */
    private int windowMode;

    public WallWindowXmlConvert(
            int x,
            int y,
            int width,
            int height,
            int windowMode,
            String subWindowId,
            String streamingChannelID) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.streamingChannelID = streamingChannelID;
        this.subWindowId = subWindowId;
        this.windowMode = windowMode;
    }

    public String getPutXMl() {
        String input =
                "<WallWindow version: '2.0' xmlns='http://www.isapi"
                        + ".org/ver20/XMLSchema'><id>"
                        + windowId
                        + "</id><wndOperateMode>uniformCoordinate</wndOperateMode><Rect"
                        + "><Coordinate><x>"
                        + x
                        + "</x><y>"
                        + y
                        + "</y></Coordinate><width>"
                        + width
                        + "</width><height>"
                        + height
                        + "</height></Rect"
                        + "><layerIdx></layerIdx><windowMode>"
                        + windowMode
                        + "</windowMode><SubWindowList><SubWindow><id>"
                        + subWindowId
                        + "</id"
                        + "><SubWindowParam><signalMode>stream id</signalMode><streamingChannelID>"
                        + streamingChannelID
                        + "</streamingChannelID></SubWindowParam></SubWindow></SubWindowList></WallWindow>";

        return input;
    }

    public String getSubWindowListXml() {

        /**
         * <WallWindow version: '2.0'
         * xmlns='http://www.isapi.org/ver20/XMLSchema'><id>16777217</id><wndOperateMode>uniformCoordinate</wndOperateMode><Rect><Coordinate><x>0</x><y>0</y></Coordinate><width>1920</width><height>1920</height></Rect><layerIdx></layerIdx><windowMode>4</windowMode><SubWindowList><SubWindow><id>1</id><SubWindowParam><signalMode>stream
         * id</signalMode><streamingChannelID>2</streamingChannelID></SubWindowParam></SubWindow><SubWindow><id>2</id><SubWindowParam><signalMode>stream
         * id</signalMode><streamingChannelID>2</streamingChannelID></SubWindowParam></SubWindow></SubWindowList></WallWindow>
         */
        return "";
    }

    public String getXMl() {

        String input =
                "<WallWindow version: '2.0' xmlns='http://www.isapi"
                        + ".org/ver20/XMLSchema'><id></id><wndOperateMode>uniformCoordinate</wndOperateMode><Rect"
                        + "><Coordinate><x>"
                        + x
                        + "</x><y>"
                        + y
                        + "</y></Coordinate><width>"
                        + width
                        + "</width><height>"
                        + height
                        + "</height></Rect"
                        + "><layerIdx></layerIdx><windowMode>"
                        + windowMode
                        + "</windowMode><SubWindowList><SubWindow><id>"
                        + subWindowId
                        + "</id"
                        + "><SubWindowParam><signalMode>stream id</signalMode><streamingChannelID>"
                        + streamingChannelID
                        + "</streamingChannelID></SubWindowParam></SubWindow></SubWindowList></WallWindow>";

        return input;
    }
}
