package com.spyker.framework.isapi.xmlparse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("Coordinate")
public class CoordinateXml {
    int x;
    int y;
}
