package com.spyker.framework.third.hikvision.data;

import lombok.Data;

/**
 * 设备操作信息
 *
 * @author spyker
 */
@Data
public class HCOpInfo {

    /** 用户句柄 */
    private int lUserID = -1;

    /** 预览通道号 */
    private int lDChannel;
}