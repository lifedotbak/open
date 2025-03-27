package com.spyker.framework.isapi.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("VideoWallCap")
public class VideoWallCap {

    @XStreamAlias("maxWallNums")
    private int maxWallNums;

    @XStreamAlias("maxWindowNums")
    private int maxWindowNums;
}