package com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.notify.cmd;

import static com.spyker.iot.vmp.gb28181.utils.XmlUtil.getText;

import com.spyker.iot.vmp.conf.UserSetting;
import com.spyker.iot.vmp.gb28181.bean.*;
import com.spyker.iot.vmp.gb28181.event.EventPublisher;
import com.spyker.iot.vmp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.notify.NotifyMessageHandler;
import com.spyker.iot.vmp.gb28181.utils.NumericUtil;
import com.spyker.iot.vmp.gb28181.utils.SipUtils;
import com.spyker.iot.vmp.service.IDeviceChannelService;
import com.spyker.iot.vmp.storager.IRedisCatchStorage;
import com.spyker.iot.vmp.storager.IVideoManagerStorage;
import com.spyker.iot.vmp.utils.DateUtil;

import gov.nist.javax.sip.message.SIPRequest;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.message.Response;

/** 移动设备位置数据通知，设备主动发起，不需要上级订阅 */
@Component
public class MobilePositionNotifyMessageExHandler extends SIPRequestProcessorParent
        implements InitializingBean, IMessageHandler {

    private final String cmdType = "MobilePosition";
    private final Logger logger =
            LoggerFactory.getLogger(MobilePositionNotifyMessageExHandler.class);
    private final ConcurrentLinkedQueue<SipMsgInfo> taskQueue = new ConcurrentLinkedQueue<>();
    @Autowired private NotifyMessageHandler notifyMessageHandler;
    @Autowired private UserSetting userSetting;
    @Autowired private IVideoManagerStorage storager;
    @Autowired private IRedisCatchStorage redisCatchStorage;
    @Autowired private IDeviceChannelService deviceChannelService;
    @Autowired private EventPublisher eventPublisher;
    @Autowired private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public void afterPropertiesSet() throws Exception {
        notifyMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element rootElement) {

        boolean isEmpty = taskQueue.isEmpty();
        taskQueue.offer(new SipMsgInfo(evt, device, rootElement));
        // 回复200 OK
        try {
            responseAck((SIPRequest) evt.getRequest(), Response.OK);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            logger.error("[命令发送失败] 移动位置通知回复: {}", e.getMessage());
        }
        if (isEmpty) {
            taskExecutor.execute(
                    () -> {
                        while (!taskQueue.isEmpty()) {
                            SipMsgInfo sipMsgInfo = taskQueue.poll();
                            try {
                                Element rootElementAfterCharset =
                                        getRootElement(
                                                sipMsgInfo.getEvt(),
                                                sipMsgInfo.getDevice().getCharset());
                                if (rootElementAfterCharset == null) {
                                    logger.warn("[移动位置通知] {}处理失败，未识别到信息体", device.getDeviceId());
                                    continue;
                                }
                                MobilePosition mobilePosition = new MobilePosition();
                                mobilePosition.setCreateTime(DateUtil.getNow());
                                if (!ObjectUtils.isEmpty(sipMsgInfo.getDevice().getName())) {
                                    mobilePosition.setDeviceName(sipMsgInfo.getDevice().getName());
                                }
                                mobilePosition.setDeviceId(sipMsgInfo.getDevice().getDeviceId());
                                mobilePosition.setChannelId(
                                        getText(rootElementAfterCharset, "DeviceID"));
                                String time = getText(rootElementAfterCharset, "Time");
                                if (ObjectUtils.isEmpty(time)) {
                                    mobilePosition.setTime(DateUtil.getNow());
                                } else {
                                    mobilePosition.setTime(SipUtils.parseTime(time));
                                }
                                mobilePosition.setLongitude(
                                        Double.parseDouble(
                                                getText(rootElementAfterCharset, "Longitude")));
                                mobilePosition.setLatitude(
                                        Double.parseDouble(
                                                getText(rootElementAfterCharset, "Latitude")));
                                if (NumericUtil.isDouble(
                                        getText(rootElementAfterCharset, "Speed"))) {
                                    mobilePosition.setSpeed(
                                            Double.parseDouble(
                                                    getText(rootElementAfterCharset, "Speed")));
                                } else {
                                    mobilePosition.setSpeed(0.0);
                                }
                                if (NumericUtil.isDouble(
                                        getText(rootElementAfterCharset, "Direction"))) {
                                    mobilePosition.setDirection(
                                            Double.parseDouble(
                                                    getText(rootElementAfterCharset, "Direction")));
                                } else {
                                    mobilePosition.setDirection(0.0);
                                }
                                if (NumericUtil.isDouble(
                                        getText(rootElementAfterCharset, "Altitude"))) {
                                    mobilePosition.setAltitude(
                                            Double.parseDouble(
                                                    getText(rootElementAfterCharset, "Altitude")));
                                } else {
                                    mobilePosition.setAltitude(0.0);
                                }
                                mobilePosition.setReportSource("Mobile Position");

                                // 更新device channel 的经纬度
                                DeviceChannel deviceChannel = new DeviceChannel();
                                deviceChannel.setDeviceId(sipMsgInfo.getDevice().getDeviceId());
                                deviceChannel.setChannelId(mobilePosition.getChannelId());
                                deviceChannel.setLongitude(mobilePosition.getLongitude());
                                deviceChannel.setLatitude(mobilePosition.getLatitude());
                                deviceChannel.setGpsTime(mobilePosition.getTime());

                                deviceChannel =
                                        deviceChannelService.updateGps(
                                                deviceChannel, sipMsgInfo.getDevice());

                                mobilePosition.setLongitudeWgs84(deviceChannel.getLongitudeWgs84());
                                mobilePosition.setLatitudeWgs84(deviceChannel.getLatitudeWgs84());
                                mobilePosition.setLongitudeGcj02(deviceChannel.getLongitudeGcj02());
                                mobilePosition.setLatitudeGcj02(deviceChannel.getLatitudeGcj02());

                                deviceChannelService.updateChannelGPS(
                                        device, deviceChannel, mobilePosition);

                            } catch (DocumentException e) {
                                logger.error("未处理的异常 ", e);
                            } catch (Exception e) {
                                logger.warn("[移动位置通知] 发现未处理的异常, \r\n{}", evt.getRequest());
                                logger.error("[移动位置通知] 异常内容： ", e);
                            }
                        }
                    });
        }
    }

    @Override
    public void handForPlatform(RequestEvent evt, ParentPlatform parentPlatform, Element element) {}
}