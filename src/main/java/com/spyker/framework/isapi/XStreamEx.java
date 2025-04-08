package com.spyker.framework.isapi;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/** 解决xml中存在节点，而model中不存在报错的问题 */
public class XStreamEx extends XStream {

    public XStreamEx(DomDriver domDriver) {
        super();
    }

    @Override
    protected MapperWrapper wrapMapper(MapperWrapper next) {
        return new MapperWrapper(next) {
            @Override
            public boolean shouldSerializeMember(
                    @SuppressWarnings("rawtypes") Class definedIn, String fieldName) {
                if (definedIn == Object.class) {
                    return false;
                }
                return super.shouldSerializeMember(definedIn, fieldName);
            }
        };
    }
}
