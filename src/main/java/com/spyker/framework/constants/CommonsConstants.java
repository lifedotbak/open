package com.spyker.framework.constants;

/**
 * 通用常量信息
 *
 * @author spyker
 */
public interface CommonsConstants {

    // 默认分页
    int DEFAULT_PAGE = 1;
    // 默认分页
    int DEFAULT_SIZE = 20;

    /** UTF-8 字符集 */
    String UTF8 = "UTF-8";

    /** GBK 字符集 */
    String GBK = "GBK";

    /** www主域 */

    /** http请求 */
    String HTTP = "http://";

    /** https请求 */
    String HTTPS = "https://";

    /** 通用成功标识 */
    String SUCCESS = "0";

    /** 验证码有效期（分钟） */
    Integer CAPTCHA_EXPIRATION = 2;

    /** 登录用户ID */
    String LOGIN_USER_ID = "login_user_id";

    String LOGIN_USER_NAME = "login_user_name";

    /** 登录用户信息 */
    String LOGIN_USER_INFO = "login_user_info";

    /** 资源映射路径 前缀 */
    String RESOURCE_PREFIX = "/profile";
}