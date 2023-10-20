package com.spyker.framework.third.hikvision.action;

import com.spyker.framework.third.hikvision.data.HCLoginInfo;
import com.spyker.framework.third.hikvision.data.HCOpInfo;
import com.spyker.framework.third.hikvision.jna.HCNetSDK;
import com.spyker.framework.third.hikvision.utils.HCInitUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

@AutoConfiguration
@Slf4j
@ConditionalOnClass(HCInitUtils.class)
public class HCLoginAction {

    @Autowired
    private HCInitUtils hCInitUtils;

    /**
     * 设备登录V40 与V30功能一致
     *
     * @param loginDeviceInfo
     * @return
     */
    public HCOpInfo login_V40(HCLoginInfo loginDeviceInfo) {

        String ip = loginDeviceInfo.getIp();
        short port = (short) loginDeviceInfo.getPort();

        String user = loginDeviceInfo.getName();
        String psw = loginDeviceInfo.getPassword();

        HCOpInfo result = new HCOpInfo();

        int lUserID = -1;// 用户句柄

        // 注册
        HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();// 设备登录信息
        HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();// 设备信息

        String m_sDeviceIP = ip;// 设备ip地址
        m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());

        String m_sUsername = user;// 设备用户名
        m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());

        String m_sPassword = psw;// 设备密码
        m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());

        m_strLoginInfo.wPort = port;
        m_strLoginInfo.bUseAsynLogin = false; // 是否异步登录：0- 否，1- 是
        m_strLoginInfo.byLoginMode = 0; // ISAPI登录
        m_strLoginInfo.write();

        HCNetSDK hCNetSDK = hCInitUtils.getHCNetSDK();

        lUserID = hCNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);
        if (lUserID == -1) {

            log.error("登录失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());

        } else {

            log.info(ip + ":设备登录成功！");

        }

        result.setLDChannel(loginDeviceInfo.getLDChannel());
        result.setLUserID(lUserID);

        return result;
    }

    public void logout(int lUserID) {

        HCNetSDK hCNetSDK = hCInitUtils.getHCNetSDK();

        // 退出程序时调用，每一台设备分别注销
        if (hCNetSDK.NET_DVR_Logout(lUserID)) {

            log.info("注销成功--->{}", lUserID);
        }

    }
}