package com.spyker.iot.vmp.gb28181.task;

import com.spyker.iot.vmp.common.CommonCallback;

/**
 * @author lin
 */
public interface ISubscribeTask extends Runnable {
    void stop(CommonCallback<Boolean> callback);
}