package com.spyker.framework.third.zlmediakit.utils;

import com.spyker.framework.third.zlmediakit.ZLMediaKitProperties;

public final class ZLMediaKitUtils {

    public static String generateRtspUrl(
            ZLMediaKitProperties zLMediaKitProperties, String urlPath) {

        String result =
                "rtsp://"
                        + zLMediaKitProperties.getIp()
                        + ":"
                        + zLMediaKitProperties.getPort()
                        + "/"
                        + urlPath;

        return result;
    }

    public static String generateFlvUrl(ZLMediaKitProperties zLMediaKitProperties, String urlPath) {

        String result =
                "http://"
                        + zLMediaKitProperties.getIp()
                        + ":"
                        + zLMediaKitProperties.getPort()
                        + "/"
                        + urlPath
                        + ".live.flv";

        return result;
    }
}