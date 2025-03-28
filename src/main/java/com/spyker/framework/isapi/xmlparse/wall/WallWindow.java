package com.spyker.framework.isapi.xmlparse.wall;

import com.spyker.framework.isapi.xmlparse.RectXml;

import lombok.Data;

import java.util.List;

@Data
public class WallWindow {

    String id;
    String wndOperateMode;
    RectXml Rect;
    String layerIdx;
    String windowMode;
    String wndShowMode;

    List<SubWindow> SubWindowList;
}
