package com.spyker.flowable.common.utils;

import cn.hutool.core.util.StrUtil;

import com.spyker.flowable.common.constants.ProcessInstanceConstant;

/** 租户工具 */
public class TenantUtil {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void put(String id) {

        THREAD_LOCAL.set(id);
    }

    public static String get() {
        String s = THREAD_LOCAL.get();
        if (StrUtil.isBlank(s)) {
            return ProcessInstanceConstant.VariableKey.DEFAULT_TENANT_ID;
        }
        return s;
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}