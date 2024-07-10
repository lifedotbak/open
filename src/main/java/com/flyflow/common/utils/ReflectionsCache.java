package com.flyflow.common.utils;

import org.reflections.Reflections;

/**
 * @author zhj
 * @version 1.0
 * @description: TODO
 * @date 2024/4/4 18:04
 */
public class ReflectionsCache {

    private static Reflections reflections;

    public static Reflections getReflections() {
        return reflections;
    }

    public static void setReflections(Reflections _reflections) {
        reflections = _reflections;
    }
}