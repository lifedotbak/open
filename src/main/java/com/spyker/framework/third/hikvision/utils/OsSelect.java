package com.spyker.framework.third.hikvision.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** 操作系统util */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OsSelect {

    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}