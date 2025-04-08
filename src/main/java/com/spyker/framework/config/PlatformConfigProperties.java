package com.spyker.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** 读取项目相关配置 */
@Component
@ConfigurationProperties(prefix = "platform", ignoreUnknownFields = true)
public class PlatformConfigProperties {

    /** 上传路径 */
    private static String profile;

    /** 获取地址开关 */
    private static boolean addressEnabled;

    private static String captchaEnabled;

    /** 验证码类型 */
    private static String captchaType;

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        PlatformConfigProperties.addressEnabled = addressEnabled;
    }

    public static String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        PlatformConfigProperties.captchaType = captchaType;
    }

    public static String getCaptchaEnabled() {
        return captchaEnabled;
    }

    public void setCaptchaEnabled(String captchaEnabled) {
        PlatformConfigProperties.captchaEnabled = captchaEnabled;
    }

    /** 获取导入上传路径 */
    public static String getImportPath() {
        return getProfile() + "/import";
    }

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        PlatformConfigProperties.profile = profile;
    }

    /** 获取头像上传路径 */
    public static String getAvatarPath() {
        return getProfile() + "/avatar";
    }

    /** 获取下载路径 */
    public static String getDownloadPath() {
        return getProfile() + "/download/";
    }

    /** 获取上传路径 */
    public static String getUploadPath() {
        return getProfile() + "/upload";
    }
}
