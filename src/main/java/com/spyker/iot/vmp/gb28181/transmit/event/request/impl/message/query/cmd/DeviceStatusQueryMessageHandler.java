package com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.query.cmd;

import static com.spyker.iot.vmp.gb28181.utils.XmlUtil.getText;

import com.spyker.iot.vmp.conf.SipConfig;
import com.spyker.iot.vmp.gb28181.bean.Device;
import com.spyker.iot.vmp.gb28181.bean.DeviceChannel;
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
import javax.sip.header.FromHeader;
import javax.sip.message.Response;

@Component
public class DeviceStatusQueryMessageHandler extends SIPRequestProcessorParent
        implements InitializingBean, IMessageHandler {

    private final String cmdType = "DeviceStatus";
    private final Logger logger = LoggerFactory.getLogger(DeviceStatusQueryMessageHandler.class);
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

        logger.info("接收到DeviceStatus查询消息");
        FromHeader fromHeader = (FromHeader) evt.getRequest().getHeader(FromHeader.NAME);
        // 回复200 OK
        try {
            responseAck((SIPRequest) evt.getRequest(), Response.OK);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            logger.error("[命令发送失败] 国标级联 DeviceStatus查询回复200OK: {}", e.getMessage());
        }
        String sn = rootElement.element("SN").getText();
        String channelId = getText(rootElement, "DeviceID");
        DeviceChannel deviceChannel =
                storager.queryChannelInParentPlatform(parentPlatform.getServerGBId(), channelId);
        if (deviceChannel == null) {
            logger.error(
                    "[平台没有该通道的使用权限]:platformId"
                            + parentPlatform.getServerGBId()
                            + "  deviceID:"
                            + channelId);
            return;
        }
        try {
            cmderFroPlatform.deviceStatusResponse(
                    parentPlatform, channelId, sn, fromHeader.getTag(), deviceChannel.isStatus());
        } catch (SipException | InvalidArgumentException | ParseException e) {
            logger.error("[命令发送失败] 国标级联 DeviceStatus查询回复: {}", e.getMessage());
        }
    }
}