package com.spyker.commons.satoken;

import cn.dev33.satoken.stp.StpUtil;

import com.spyker.commons.entity.SysUser;
import com.spyker.framework.constants.CommonsConstants;

public class SaTokenUtils {

    public static void login(SysUser sysUser) {
        StpUtil.login(sysUser.getId());

        StpUtil.getSession().set(CommonsConstants.LOGIN_USER_ID, sysUser.getId());
        StpUtil.getSession().set(CommonsConstants.LOGIN_USER_NAME, sysUser.getUserName());
        StpUtil.getSession().set(CommonsConstants.LOGIN_USER_INFO, sysUser);
    }

    public static SysUser getLoginUser() {

        Object object = StpUtil.getSession().get(CommonsConstants.LOGIN_USER_INFO);

        if (null != object) {
            return (SysUser) object;
        }

        return null;
    }

    public static String getLoginUserName() {

        Object object = StpUtil.getSession().get(CommonsConstants.LOGIN_USER_NAME);

        if (null != object) {
            return (String) object;
        }

        return null;
    }

    public static String getLoginUserId() {

        Object object = StpUtil.getSession().get(CommonsConstants.LOGIN_USER_ID);

        if (null != object) {
            return (String) object;
        }

        return null;
    }
}
