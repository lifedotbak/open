package com.spyker.framework.constants;

import io.jsonwebtoken.Claims;

/**
 * 通用常量信息
 *
 * @author spyker
 */
public class CommonsConstants {

    // 默认分页
    public static final int DEFAULT_PAGE = 1;
    // 默认分页
    public static final int DEFAULT_SIZE = 20;
    // 导出最大数值
    public static final Integer EXPORT_MAX_LIMIT = 99999;

    /** UTF-8 字符集 */
    public static final String UTF8 = "UTF-8";

    /** GBK 字符集 */
    public static final String GBK = "GBK";

    /** www主域 */
    public static final String WWW = "www.";

    /** http请求 */
    public static final String HTTP = "http://";

    /** https请求 */
    public static final String HTTPS = "https://";

    /** 通用成功标识 */
    public static final String SUCCESS = "0";

    /** 通用失败标识 */
    public static final String FAIL = "1";

    /** 登录成功 */
    public static final String LOGIN_SUCCESS = "Success";

    /** 注销 */
    public static final String LOGOUT = "Logout";

    /** 注册 */
    public static final String REGISTER = "Register";

    /** 登录失败 */
    public static final String LOGIN_FAIL = "Error";

    /** 验证码有效期（分钟） */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /** 令牌 */
    public static final String TOKEN = "token";

    /** 令牌前缀 */
    public static final String TOKEN_PREFIX = "Bearer ";

    /** 令牌前缀 */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /** 用户ID */
    public static final String JWT_USERID = "userid";

    /** 用户名称 */
    public static final String JWT_USERNAME = Claims.SUBJECT;

    /** 用户头像 */
    public static final String JWT_AVATAR = "avatar";

    /** 创建时间 */
    public static final String JWT_CREATED = "created";

    /** 用户权限 */
    public static final String JWT_AUTHORITIES = "authorities";

    /** 资源映射路径 前缀 */
    public static final String RESOURCE_PREFIX = "/profile";

    /** RMI 远程方法调用 */
    public static final String LOOKUP_RMI = "rmi:";

    /** LDAP 远程方法调用 */
    public static final String LOOKUP_LDAP = "ldap:";

    private CommonsConstants() {}
}