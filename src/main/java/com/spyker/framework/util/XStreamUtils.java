package com.spyker.framework.util;

import com.thoughtworks.xstream.XStream;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class XStreamUtils {

    private static final String XML_HEARD = "<?xml version='1.0' encoding='UTF-8'?>";

    public static String java2Xml(Object object, String aliasTypeName) {

        String result = "";
        try {
            XStream xs = new XStream();

            if (null != aliasTypeName && aliasTypeName.length() > 0) {
                xs.aliasType(aliasTypeName, object.getClass());
            }

            result = XML_HEARD + xs.toXML(object);
        } catch (Exception e) {
            log.error("XStreamUtils:java2Xml------>{}", e);
        }
        return result;
    }

    public static Object xml2java(String xml, Class<?> classValue) {
        XStream xs = new XStream();

        xs.alias("xml", classValue);

        return xs.fromXML(xml);
    }
}