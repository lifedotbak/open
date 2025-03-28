package com.spyker.framework.isapi.convert;

/** 绑定输出口到墙 */
public class WallOutputListXmlConvert {

    private final String outputID;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public WallOutputListXmlConvert(String outputID, int x, int y, int width, int height) {
        this.outputID = outputID;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getXMl() {

        String input =
                "<WallOutputList xmlns: \"http://www.isapi.org/ver20/XMLSchema\" version=\"2.0\">"
                        + "<WallOutput><outputID>"
                        + outputID
                        + "</outputID><Rect><Coordinate><x>"
                        + x
                        + "</x><y>"
                        + y
                        + "</y></Coordinate"
                        + "><width>"
                        + width
                        + "</width><height>"
                        + height
                        + "</height></Rect></WallOutput></WallOutputList>";

        return input;
    }
}
