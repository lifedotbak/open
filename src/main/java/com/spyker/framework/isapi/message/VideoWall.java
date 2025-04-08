package com.spyker.framework.isapi.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
public class VideoWall {

    @XStreamAlias("id")
    String id;

    String name;

    String wndStaticMode;

    String streamFailedMode;

    String decodingResourceTipEnabled;
}
