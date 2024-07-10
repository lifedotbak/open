package com.flyflow.common.utils;

import static com.flyflow.common.constants.ProcessInstanceConstant.VariableKey.DEFAULT_TENANT_ID;

import cn.hutool.core.util.StrUtil;

/** 租户工具 */
public class TenantUtil {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void put(String id) {

        THREAD_LOCAL.set(id);
    }

    public static String get() {
        String s = THREAD_LOCAL.get();
        if (StrUtil.isBlank(s)) {
            return DEFAULT_TENANT_ID;
        }
        return s;
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}