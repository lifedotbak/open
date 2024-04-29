package com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.response.cmd;

import static com.spyker.iot.vmp.gb28181.utils.XmlUtil.getText;

import com.alibaba.fastjson2.JSONObject;
import com.spyker.iot.vmp.gb28181.bean.Device;
import com.spyker.iot.vmp.gb28181.bean.ParentPlatform;
import com.spyker.iot.vmp.gb28181.transmit.callback.DeferredResultHolder;
import com.spyker.iot.vmp.gb28181.transmit.callback.RequestMessage;
import com.spyker.iot.vmp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.response.ResponseMessageHandler;
import com.spyker.iot.vmp.gb28181.utils.XmlUtil;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sip.RequestEvent;

@Component
public class DeviceConfigResponseMessageHandler extends SIPRequestProcessorParent
        implements InitializingBean, IMessageHandler {

    private final String cmdType = "DeviceConfig";
    private final Logger logger = LoggerFactory.getLogger(DeviceConfigResponseMessageHandler.class);
    @Autowired private ResponseMessageHandler responseMessageHandler;

    @Autowired private DeferredResultHolder deferredResultHolder;

    @Override
    public void afterPropertiesSet() throws Exception {
        responseMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {
        JSONObject json = new JSONObject();
        XmlUtil.node2Json(element, json);
        String channelId = getText(element, "DeviceID");
        if (logger.isDebugEnabled()) {
            logger.debug(json.toJSONString());
        }
        String key =
                DeferredResultHolder.CALLBACK_CMD_DEVICECONFIG + device.getDeviceId() + channelId;
        RequestMessage msg = new RequestMessage();
        msg.setKey(key);
        msg.setData(json);
        deferredResultHolder.invokeAllResult(msg);
    }

    @Override
    public void handForPlatform(
            RequestEvent evt, ParentPlatform parentPlatform, Element rootElement) {}
}