package com.spyker.framework.util;

import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class GpsUtil {

    private static final Logger logger = LoggerFactory.getLogger(GpsUtil.class);

    @Data
    public static class BaiduPoint {
        String bdLng;

        String bdLat;
    }

    public static BaiduPoint Wgs84ToBd09(String xx, String yy) {

        double lng = Double.parseDouble(xx);
        double lat = Double.parseDouble(yy);
        Double[] gcj02 = CoordTransform.WGS84ToGCJ02(lng, lat);
        Double[] doubles = CoordTransform.GCJ02ToBD09(gcj02[0], gcj02[1]);
        BaiduPoint bdPoint = new BaiduPoint();
        bdPoint.setBdLng(doubles[0] + "");
        bdPoint.setBdLat(doubles[1] + "");
        return bdPoint;
    }

    /**
     * BASE64解码
     *
     * @param str
     * @return string
     */
    public static byte[] decode(String str) {
        byte[] bt = null;
        final Base64.Decoder decoder = Base64.getDecoder();
        bt = decoder.decode(str); // .decodeBuffer(str);
        return bt;
    }
}
