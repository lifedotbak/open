package com.spyker.framework.util.reflect;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public final class ReflectObjectUtils {

    public static Map<String, String> getKeyAndValue(Object obj) {
        Map<String, String> result = new HashMap<String, String>();

        // 得到类对象
        Class userCla = obj.getClass();

        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                // 得到此属性的值
                val = f.get(obj);

                if (null != val) {
                    /** 简单类型判断 */
                    if (val instanceof String
                            || val instanceof Byte
                            || val instanceof Short
                            || val instanceof Integer
                            || val instanceof Long
                            || val instanceof Character
                            || val instanceof Float
                            || val instanceof Double
                            || val instanceof Long
                            || val instanceof Long) {
                        if ("openid".equals(f.getName())) {
                            if (!StringUtils.isBlank(val.toString())) {
                                result.put(f.getName(), val.toString()); // 设置键值
                            }
                        } else {
                            result.put(f.getName(), val.toString()); // 设置键值
                        }
                    }
                }

            } catch (Exception e) {

                log.error("error -->{}", e);
            }
        }

        log.error("result -->{}", result);

        return result;
    }
}