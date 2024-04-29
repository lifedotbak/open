package com.spyker.iot.vmp.service.bean;

public interface PlayBackCallback<T> {

    void call(PlayBackResult<T> msg);
}