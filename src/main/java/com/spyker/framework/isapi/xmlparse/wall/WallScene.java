package com.spyker.framework.isapi.xmlparse.wall;

import lombok.Data;

@Data
public class WallScene {

    String id;
    String name;

    Boolean isRunning = false;

    public String getPutXml() {
        String input =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><WallScene xmlns=\"http://www.isapi.org/ver20/XMLSchema\" version=\"2.0\"><id></id><name></name></WallScene>";

        return input.replace("</id>", id + "</id>").replace("</name>", name + "</name>");
    }

    public String getSaveDataXml() {
        String input =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request xmlns=\"http://www.isapi.org/ver20/XMLSchema\" version=\"2.0\"></Request>";

        return input;
    }

    public String getXMl() {
        String input =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><WallScene xmlns=\"http://www.isapi.org/ver20/XMLSchema\" version=\"2.0\"><name></name></WallScene>";

        return input.replace("</id>", id + "</id>").replace("</name>", name + "</name>");
    }
}
