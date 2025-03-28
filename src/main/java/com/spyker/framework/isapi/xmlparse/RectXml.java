package com.spyker.framework.isapi.xmlparse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("Rect")
public class RectXml {
    CoordinateXml Coordinate;
    int width;
    int height;
}
