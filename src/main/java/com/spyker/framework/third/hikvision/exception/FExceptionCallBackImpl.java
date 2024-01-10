package com.spyker.framework.third.hikvision.exception;

import com.spyker.framework.third.hikvision.jna.HCNetSDK;
import com.sun.jna.Pointer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FExceptionCallBackImpl implements HCNetSDK.FExceptionCallBack {

    @Override
    public void invoke(int dwType, int lUserID, int lHandle, Pointer pUser) {

        log.info("异常事件类型:--->{}", dwType);
    }
}