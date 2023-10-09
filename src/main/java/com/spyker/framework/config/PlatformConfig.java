package com.spyker.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author platform
 */
@Component
@ConfigurationProperties(prefix = "platform", ignoreUnknownFields = true)
public class PlatformConfig {
    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyrightYear;

    /**
     * 上传路径
     */
    private static String profile;

    /**
     * 获取地址开关
     */
    private static boolean addressEnabled;

    /**
     * 验证码类型
     */
    private static String captchaType;

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public static String getCaptchaType() {
        return captchaType;
    }

    public static String getProfile() {
        return profile;
    }

    /**
     * 获取导入上传路径
     */
    public static String getImportPath() {
        return profile + "/import";
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath() {
        return profile + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath() {
        return profile + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath() {
        return profile + "/upload";
    }
}