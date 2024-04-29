package com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.query.cmd;

import com.spyker.iot.vmp.conf.SipConfig;
import com.spyker.iot.vmp.gb28181.bean.Device;
import com.spyker.iot.vmp.gb28181.bean.ParentPlatform;
import com.spyker.iot.vmp.gb28181.event.EventPublisher;
import com.spyker.iot.vmp.gb28181.transmit.cmd.impl.SIPCommanderFroPlatform;
import com.spyker.iot.vmp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.query.QueryMessageHandler;
import com.spyker.iot.vmp.storager.IVideoManagerStorage;

import gov.nist.javax.sip.message.SIPRequest;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.message.Response;

@Component
public class AlarmQueryMessageHandler extends SIPRequestProcessorParent
        implements InitializingBean, IMessageHandler {

    private final String cmdType = "Alarm";
    private final Logger logger = LoggerFactory.getLogger(AlarmQueryMessageHandler.class);
    @Autowired private QueryMessageHandler queryMessageHandler;

    @Autowired private IVideoManagerStorage storager;

    @Autowired private SIPCommanderFroPlatform cmderFroPlatform;

    @Autowired private SipConfig config;

    @Autowired private EventPublisher publisher;

    @Override
    public void afterPropertiesSet() throws Exception {
        queryMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {}

    @Override
    public void handForPlatform(
            RequestEvent evt, ParentPlatform parentPlatform, Element rootElement) {

        logger.info("不支持alarm查询");
        try {
            responseAck(
                    (SIPRequest) evt.getRequest(), Response.NOT_FOUND, "not support alarm query");
        } catch (SipException | InvalidArgumentException | ParseException e) {
            logger.error("[命令发送失败] 国标级联 alarm查询回复200OK: {}", e.getMessage());
        }
    }
}