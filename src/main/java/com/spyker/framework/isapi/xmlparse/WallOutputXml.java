package com.spyker.framework.isapi.xmlparse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("WallOutput")
public class WallOutputXml {
    int id;
    int outputID;
    RectXml Rect;
}
