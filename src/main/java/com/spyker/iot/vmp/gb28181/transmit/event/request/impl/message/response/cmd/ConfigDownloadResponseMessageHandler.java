package com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.response.cmd;

import static com.spyker.iot.vmp.gb28181.utils.XmlUtil.getText;

import com.alibaba.fastjson2.JSONObject;
import com.spyker.iot.vmp.gb28181.bean.Device;
import com.spyker.iot.vmp.gb28181.bean.ParentPlatform;
import com.spyker.iot.vmp.gb28181.event.EventPublisher;
import com.spyker.iot.vmp.gb28181.transmit.callback.DeferredResultHolder;
import com.spyker.iot.vmp.gb28181.transmit.callback.RequestMessage;
import com.spyker.iot.vmp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.response.ResponseMessageHandler;
import com.spyker.iot.vmp.gb28181.utils.XmlUtil;

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
public class ConfigDownloadResponseMessageHandler extends SIPRequestProcessorParent
        implements InitializingBean, IMessageHandler {

    private final String cmdType = "ConfigDownload";
    private final Logger logger =
            LoggerFactory.getLogger(ConfigDownloadResponseMessageHandler.class);
    @Autowired private ResponseMessageHandler responseMessageHandler;

    @Autowired private EventPublisher publisher;

    @Autowired private DeferredResultHolder deferredResultHolder;

    @Override
    public void afterPropertiesSet() throws Exception {
        responseMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {
        String channelId = getText(element, "DeviceID");
        String key =
                DeferredResultHolder.CALLBACK_CMD_CONFIGDOWNLOAD + device.getDeviceId() + channelId;
        try {
            // 回复200 OK
            responseAck((SIPRequest) evt.getRequest(), Response.OK);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            logger.error("[命令发送失败] 设备配置查询: {}", e.getMessage());
        }
        // 此处是对本平台发出DeviceControl指令的应答
        JSONObject json = new JSONObject();
        XmlUtil.node2Json(element, json);
        if (logger.isDebugEnabled()) {
            logger.debug(json.toJSONString());
        }
        RequestMessage msg = new RequestMessage();
        msg.setKey(key);
        msg.setData(json);
        deferredResultHolder.invokeAllResult(msg);
    }

    @Override
    public void handForPlatform(RequestEvent evt, ParentPlatform parentPlatform, Element element) {
        // 不会收到上级平台的心跳信息

    }
}