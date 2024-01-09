package com.spyker.framework.third.hikvision.utils;

import com.spyker.framework.third.hikvision.jna.HCNetSDK;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class CommonUtil {

    public static String generatePlayBackRtspName(String ip, int lDChannel) {

        String result = "playback/";

        result = ip + "_" + lDChannel + "_" + System.currentTimeMillis();

        return result;
    }

    public static String generateFileName(String ip, int lDChannel) {

        String result = "";

        result = ip + "_" + lDChannel + "_" + System.currentTimeMillis();

        return result;
    }

    public static String parseTime(int time) {
        int year = (time >> 26) + 2000;
        int month = time >> 22 & 15;
        int day = time >> 17 & 31;
        int hour = time >> 12 & 31;
        int min = time >> 6 & 63;
        int second = time >> 0 & 63;
        String sTime = year + "-" + month + "-" + day + "-" + hour + ":" + min + ":" + second;
        return sTime;
    }

    /**
     * 获取海康录像机格式的时间
     *
     * @param time
     * @return
     */
    public static HCNetSDK.NET_DVR_TIME getHkTime(Date time) {
        HCNetSDK.NET_DVR_TIME structTime = new HCNetSDK.NET_DVR_TIME();
        String str = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);
        String[] times = str.split("-");
        structTime.dwYear = Integer.parseInt(times[0]);
        structTime.dwMonth = Integer.parseInt(times[1]);
        structTime.dwDay = Integer.parseInt(times[2]);
        structTime.dwHour = Integer.parseInt(times[3]);
        structTime.dwMinute = Integer.parseInt(times[4]);
        structTime.dwSecond = Integer.parseInt(times[5]);
        return structTime;
    }
}