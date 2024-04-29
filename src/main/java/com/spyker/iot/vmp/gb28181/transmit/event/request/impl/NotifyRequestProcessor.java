package com.spyker.iot.vmp.gb28181.transmit.event.request.impl;

import com.spyker.iot.vmp.conf.SipConfig;
import com.spyker.iot.vmp.conf.UserSetting;
import com.spyker.iot.vmp.gb28181.bean.*;
import com.spyker.iot.vmp.gb28181.event.EventPublisher;
import com.spyker.iot.vmp.gb28181.transmit.SIPProcessorObserver;
import com.spyker.iot.vmp.gb28181.transmit.callback.DeferredResultHolder;
import com.spyker.iot.vmp.gb28181.transmit.cmd.impl.SIPCommander;
import com.spyker.iot.vmp.gb28181.transmit.event.request.ISIPRequestProcessor;
import com.spyker.iot.vmp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.spyker.iot.vmp.gb28181.utils.NumericUtil;
import com.spyker.iot.vmp.gb28181.utils.SipUtils;
import com.spyker.iot.vmp.gb28181.utils.XmlUtil;
import com.spyker.iot.vmp.service.IDeviceChannelService;
import com.spyker.iot.vmp.storager.IRedisCatchStorage;
import com.spyker.iot.vmp.storager.IVideoManagerStorage;
import com.spyker.iot.vmp.utils.DateUtil;
import com.spyker.iot.vmp.utils.redis.RedisUtil;

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
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.header.FromHeader;
import javax.sip.message.Response;

/** SIP命令类型： NOTIFY请求,这是作为上级发送订阅请求后，设备才会响应的 */
@Component
public class NotifyRequestProcessor extends SIPRequestProcessorParent
        implements InitializingBean, ISIPRequestProcessor {

    private static final Logger logger = LoggerFactory.getLogger(NotifyRequestProcessor.class);
    private final String method = "NOTIFY";
    private final ConcurrentLinkedQueue<HandlerCatchData> taskQueue = new ConcurrentLinkedQueue<>();
    private final int maxQueueCount = 30000;
    @Autowired private UserSetting userSetting;
    @Autowired private IVideoManagerStorage storager;
    @Autowired private EventPublisher eventPublisher;
    @Autowired private SipConfig sipConfig;
    @Autowired private IRedisCatchStorage redisCatchStorage;
    @Autowired private EventPublisher publisher;
    @Autowired private SIPProcessorObserver sipProcessorObserver;
    @Autowired private IDeviceChannelService deviceChannelService;
    @Autowired private NotifyRequestForCatalogProcessor notifyRequestForCatalogProcessor;
    @Autowired private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 添加消息处理的订阅
        sipProcessorObserver.addRequestProcessor(method, this);
    }

    @Override
    public void process(RequestEvent evt) {
        try {
            if (taskQueue.size() >= userSetting.getMaxNotifyCountQueue()) {
                responseAck((SIPRequest) evt.getRequest(), Response.BUSY_HERE, null, null);
                logger.error(
                        "[notify] 待处理消息队列已满 {}，返回486 BUSY_HERE，消息不做处理",
                        userSetting.getMaxNotifyCountQueue());
                return;
            } else {
                responseAck((SIPRequest) evt.getRequest(), Response.OK, null, null);
            }

        } catch (SipException | InvalidArgumentException | ParseException e) {
            logger.error("未处理的异常 ", e);
        }
        boolean runed = !taskQueue.isEmpty();
        logger.info("[notify] 待处理消息数量： {}", taskQueue.size());
        taskQueue.offer(new HandlerCatchData(evt, null, null));
        if (!runed) {
            taskExecutor.execute(
                    () -> {
                        while (!taskQueue.isEmpty()) {
                            try {
                                HandlerCatchData take = taskQueue.poll();
                                if (take == null) {
                                    continue;
                                }
                                Element rootElement = getRootElement(take.getEvt());
                                if (rootElement == null) {
                                    logger.error(
                                            "处理NOTIFY消息时未获取到消息体,{}", take.getEvt().getRequest());
                                    continue;
                                }
                                String cmd = XmlUtil.getText(rootElement, "CmdType");

                                if (CmdType.CATALOG.equals(cmd)) {
                                    logger.info("接收到Catalog通知");
                                    notifyRequestForCatalogProcessor.process(take.getEvt());
                                } else if (CmdType.ALARM.equals(cmd)) {
                                    logger.info("接收到Alarm通知");
                                    processNotifyAlarm(take.getEvt());
                                } else if (CmdType.MOBILE_POSITION.equals(cmd)) {
                                    logger.info("接收到MobilePosition通知");
                                    processNotifyMobilePosition(take.getEvt());
                                } else {
                                    logger.info("接收到消息：" + cmd);
                                }
                            } catch (DocumentException e) {
                                logger.error("处理NOTIFY消息时错误", e);
                            }
                        }
                    });
        }
    }

    /**
     * 处理MobilePosition移动位置Notify
     *
     * @param evt
     */
    private void processNotifyMobilePosition(RequestEvent evt) {
        try {
            FromHeader fromHeader = (FromHeader) evt.getRequest().getHeader(FromHeader.NAME);
            String deviceId = SipUtils.getUserIdFromFromHeader(fromHeader);

            // 回复 200 OK
            Element rootElement = getRootElement(evt);
            if (rootElement == null) {
                logger.error("处理MobilePosition移动位置Notify时未获取到消息体,{}", evt.getRequest());
                return;
            }

            MobilePosition mobilePosition = new MobilePosition();
            mobilePosition.setCreateTime(DateUtil.getNow());

            Element deviceIdElement = rootElement.element("DeviceID");
            String channelId = deviceIdElement.getTextTrim();
            Device device = redisCatchStorage.getDevice(deviceId);

            if (device == null) {
                device = redisCatchStorage.getDevice(channelId);
                if (device == null) {
                    // 根据通道id查询设备Id
                    List<Device> deviceList = deviceChannelService.getDeviceByChannelId(channelId);
                    if (deviceList.size() > 0) {
                        device = deviceList.get(0);
                    }
                }
            }
            if (device == null) {
                logger.warn("[mobilePosition移动位置Notify] 未找到通道{}所属的设备", channelId);
                return;
            }
            if (!ObjectUtils.isEmpty(device.getName())) {
                mobilePosition.setDeviceName(device.getName());
            }

            mobilePosition.setDeviceId(device.getDeviceId());
            mobilePosition.setChannelId(channelId);
            String time = XmlUtil.getText(rootElement, "Time");
            if (ObjectUtils.isEmpty(time)) {
                mobilePosition.setTime(DateUtil.getNow());
            } else {
                mobilePosition.setTime(SipUtils.parseTime(time));
            }

            mobilePosition.setLongitude(
                    Double.parseDouble(XmlUtil.getText(rootElement, "Longitude")));
            mobilePosition.setLatitude(
                    Double.parseDouble(XmlUtil.getText(rootElement, "Latitude")));
            if (NumericUtil.isDouble(XmlUtil.getText(rootElement, "Speed"))) {
                mobilePosition.setSpeed(Double.parseDouble(XmlUtil.getText(rootElement, "Speed")));
            } else {
                mobilePosition.setSpeed(0.0);
            }
            if (NumericUtil.isDouble(XmlUtil.getText(rootElement, "Direction"))) {
                mobilePosition.setDirection(
                        Double.parseDouble(XmlUtil.getText(rootElement, "Direction")));
            } else {
                mobilePosition.setDirection(0.0);
            }
            if (NumericUtil.isDouble(XmlUtil.getText(rootElement, "Altitude"))) {
                mobilePosition.setAltitude(
                        Double.parseDouble(XmlUtil.getText(rootElement, "Altitude")));
            } else {
                mobilePosition.setAltitude(0.0);
            }
            logger.info(
                    "[收到移动位置订阅通知]：{}/{}->{}.{}",
                    mobilePosition.getDeviceId(),
                    mobilePosition.getChannelId(),
                    mobilePosition.getLongitude(),
                    mobilePosition.getLatitude());
            mobilePosition.setReportSource("Mobile Position");

            // 更新device channel 的经纬度
            DeviceChannel deviceChannel = new DeviceChannel();
            deviceChannel.setDeviceId(device.getDeviceId());
            deviceChannel.setChannelId(channelId);
            deviceChannel.setLongitude(mobilePosition.getLongitude());
            deviceChannel.setLatitude(mobilePosition.getLatitude());
            deviceChannel.setGpsTime(mobilePosition.getTime());
            deviceChannel = deviceChannelService.updateGps(deviceChannel, device);

            mobilePosition.setLongitudeWgs84(deviceChannel.getLongitudeWgs84());
            mobilePosition.setLatitudeWgs84(deviceChannel.getLatitudeWgs84());
            mobilePosition.setLongitudeGcj02(deviceChannel.getLongitudeGcj02());
            mobilePosition.setLatitudeGcj02(deviceChannel.getLatitudeGcj02());

            deviceChannelService.updateChannelGPS(device, deviceChannel, mobilePosition);

        } catch (DocumentException e) {
            logger.error("未处理的异常 ", e);
        }
    }

    /***
     * 处理alarm设备报警Notify
     *
     * @param evt
     */
    private void processNotifyAlarm(RequestEvent evt) {
        if (!sipConfig.isAlarm()) {
            return;
        }
        try {
            FromHeader fromHeader = (FromHeader) evt.getRequest().getHeader(FromHeader.NAME);
            String deviceId = SipUtils.getUserIdFromFromHeader(fromHeader);

            Element rootElement = getRootElement(evt);
            if (rootElement == null) {
                logger.error("处理alarm设备报警Notify时未获取到消息体{}", evt.getRequest());
                return;
            }
            Element deviceIdElement = rootElement.element("DeviceID");
            String channelId = deviceIdElement.getText();

            Device device = redisCatchStorage.getDevice(deviceId);
            if (device == null) {
                logger.warn("[ NotifyAlarm ] 未找到设备：{}", deviceId);
                return;
            }
            rootElement = getRootElement(evt, device.getCharset());
            if (rootElement == null) {
                logger.warn("[ NotifyAlarm ] content cannot be null, {}", evt.getRequest());
                return;
            }
            DeviceAlarm deviceAlarm = new DeviceAlarm();
            deviceAlarm.setDeviceId(deviceId);
            deviceAlarm.setAlarmPriority(XmlUtil.getText(rootElement, "AlarmPriority"));
            deviceAlarm.setAlarmMethod(XmlUtil.getText(rootElement, "AlarmMethod"));
            String alarmTime = XmlUtil.getText(rootElement, "AlarmTime");
            if (alarmTime == null) {
                logger.warn("[ NotifyAlarm ] AlarmTime cannot be null");
                return;
            }
            deviceAlarm.setAlarmTime(DateUtil.ISO8601Toyyyy_MM_dd_HH_mm_ss(alarmTime));
            if (XmlUtil.getText(rootElement, "AlarmDescription") == null) {
                deviceAlarm.setAlarmDescription("");
            } else {
                deviceAlarm.setAlarmDescription(XmlUtil.getText(rootElement, "AlarmDescription"));
            }
            if (NumericUtil.isDouble(XmlUtil.getText(rootElement, "Longitude"))) {
                deviceAlarm.setLongitude(
                        Double.parseDouble(XmlUtil.getText(rootElement, "Longitude")));
            } else {
                deviceAlarm.setLongitude(0.00);
            }
            if (NumericUtil.isDouble(XmlUtil.getText(rootElement, "Latitude"))) {
                deviceAlarm.setLatitude(
                        Double.parseDouble(XmlUtil.getText(rootElement, "Latitude")));
            } else {
                deviceAlarm.setLatitude(0.00);
            }
            logger.info("[收到Notify-Alarm]：{}/{}", device.getDeviceId(), deviceAlarm.getChannelId());
            if ("4".equals(deviceAlarm.getAlarmMethod())) {
                MobilePosition mobilePosition = new MobilePosition();
                mobilePosition.setChannelId(channelId);
                mobilePosition.setCreateTime(DateUtil.getNow());
                mobilePosition.setDeviceId(deviceAlarm.getDeviceId());
                mobilePosition.setTime(deviceAlarm.getAlarmTime());
                mobilePosition.setLongitude(deviceAlarm.getLongitude());
                mobilePosition.setLatitude(deviceAlarm.getLatitude());
                mobilePosition.setReportSource("GPS Alarm");

                // 更新device channel 的经纬度
                DeviceChannel deviceChannel = new DeviceChannel();
                deviceChannel.setDeviceId(device.getDeviceId());
                deviceChannel.setChannelId(channelId);
                deviceChannel.setLongitude(mobilePosition.getLongitude());
                deviceChannel.setLatitude(mobilePosition.getLatitude());
                deviceChannel.setGpsTime(mobilePosition.getTime());

                deviceChannel = deviceChannelService.updateGps(deviceChannel, device);

                mobilePosition.setLongitudeWgs84(deviceChannel.getLongitudeWgs84());
                mobilePosition.setLatitudeWgs84(deviceChannel.getLatitudeWgs84());
                mobilePosition.setLongitudeGcj02(deviceChannel.getLongitudeGcj02());
                mobilePosition.setLatitudeGcj02(deviceChannel.getLatitudeGcj02());

                deviceChannelService.updateChannelGPS(device, deviceChannel, mobilePosition);
            }

            // 回复200 OK
            if (redisCatchStorage.deviceIsOnline(deviceId)) {
                publisher.deviceAlarmEventPublish(deviceAlarm);
            }
        } catch (DocumentException e) {
            logger.error("未处理的异常 ", e);
        }
    }

    public void setCmder(SIPCommander cmder) {}

    public void setStorager(IVideoManagerStorage storager) {
        this.storager = storager;
    }

    public void setPublisher(EventPublisher publisher) {
        this.publisher = publisher;
    }

    public void setRedis(RedisUtil redis) {}

    public void setDeferredResultHolder(DeferredResultHolder deferredResultHolder) {}

    public IRedisCatchStorage getRedisCatchStorage() {
        return redisCatchStorage;
    }

    public void setRedisCatchStorage(IRedisCatchStorage redisCatchStorage) {
        this.redisCatchStorage = redisCatchStorage;
    }
}