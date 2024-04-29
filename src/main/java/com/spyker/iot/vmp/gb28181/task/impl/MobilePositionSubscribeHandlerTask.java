package com.spyker.iot.vmp.gb28181.task.impl;

import com.spyker.iot.vmp.common.CommonCallback;
import com.spyker.iot.vmp.gb28181.task.ISubscribeTask;
import com.spyker.iot.vmp.service.IPlatformService;
import com.spyker.iot.vmp.utils.SpringBeanFactory;

/**
 * 向已经订阅(移动位置)的上级发送MobilePosition消息
 *
 * @author lin
 */
public class MobilePositionSubscribeHandlerTask implements ISubscribeTask {

    private final IPlatformService platformService;
    private final String platformId;

    public MobilePositionSubscribeHandlerTask(String platformId) {
        this.platformService = SpringBeanFactory.getBean("platformServiceImpl");
        this.platformId = platformId;
    }

    @Override
    public void run() {
        platformService.sendNotifyMobilePosition(this.platformId);
    }

    @Override
    public void stop(CommonCallback<Boolean> callback) {}
}