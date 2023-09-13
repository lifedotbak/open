package com.spyker.framework.hikvision.utils;

/**
 * 操作系统util
 */
public final class OsSelect {

    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}