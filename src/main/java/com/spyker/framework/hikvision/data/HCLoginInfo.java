package com.spyker.framework.hikvision.data;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设备登录信息
 *
 * @author spyker
 */
@Data
@Accessors(chain = true)
public class HCLoginInfo {

    private String ip;
    private int port;
    private String name;
    private String password;
    private int lDChannel;
}