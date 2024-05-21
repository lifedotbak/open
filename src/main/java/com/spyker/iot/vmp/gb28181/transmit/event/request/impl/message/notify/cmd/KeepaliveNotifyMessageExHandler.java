package com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.notify.cmd;

import com.spyker.iot.vmp.common.VideoManagerConstants;
import com.spyker.iot.vmp.conf.DynamicTask;
import com.spyker.iot.vmp.conf.UserSetting;
import com.spyker.iot.vmp.gb28181.bean.Device;
import com.spyker.iot.vmp.gb28181.bean.ParentPlatform;
import com.spyker.iot.vmp.gb28181.bean.RemoteAddressInfo;
import com.spyker.iot.vmp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.spyker.iot.vmp.gb28181.transmit.event.request.impl.message.notify.NotifyMessageHandler;
import com.spyker.iot.vmp.gb28181.utils.SipUtils;
import com.spyker.iot.vmp.service.IDeviceService;
import com.spyker.iot.vmp.utils.DateUtil;

import gov.nist.javax.sip.message.SIPRequest;

import org.apache.commons.lang3.ObjectUtils;
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

/** 状态信息(心跳)报送 */
@Component
public class KeepaliveNotifyMessageExHandler extends SIPRequestProcessorParent
        implements InitializingBean, IMessageHandler {

    private static final String cmdType = "Keepalive";
    private final Logger logger = LoggerFactory.getLogger(KeepaliveNotifyMessageExHandler.class);
    @Autowired private NotifyMessageHandler notifyMessageHandler;

    @Autowired private IDeviceService deviceService;

    @Autowired private UserSetting userSetting;

    @Autowired private DynamicTask dynamicTask;

    @Override
    public void afterPropertiesSet() throws Exception {
        notifyMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {
        if (device == null) {
            // 未注册的设备不做处理
            return;
        }
        SIPRequest request = (SIPRequest) evt.getRequest();
        logger.info(
                "[收到心跳] device: {}, callId: {}",
                device.getDeviceId(),
                request.getCallIdHeader().getCallId());

        // 回复200 OK
        try {
            responseAck(request, Response.OK);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            logger.error("[命令发送失败] 心跳回复: {}", e.getMessage());
        }
        if (!ObjectUtils.isEmpty(device.getKeepaliveTime())
                && DateUtil.getDifferenceForNow(device.getKeepaliveTime()) <= 3000L) {
            logger.info(
                    "[收到心跳] 心跳发送过于频繁，已忽略 device: {}, callId: {}",
                    device.getDeviceId(),
                    request.getCallIdHeader().getCallId());
            return;
        }

        RemoteAddressInfo remoteAddressInfo =
                SipUtils.getRemoteAddressFromRequest(
                        request, userSetting.getSipUseSourceIpAsRemoteAddress());
        if (!device.getIp().equalsIgnoreCase(remoteAddressInfo.getIp())
                || device.getPort() != remoteAddressInfo.getPort()) {
            logger.info(
                    "[收到心跳] 设备{}地址变化, 远程地址为: {}:{}",
                    device.getDeviceId(),
                    remoteAddressInfo.getIp(),
                    remoteAddressInfo.getPort());
            device.setPort(remoteAddressInfo.getPort());
            device.setHostAddress(
                    remoteAddressInfo
                            .getIp()
                            .concat(":")
                            .concat(String.valueOf(remoteAddressInfo.getPort())));
            device.setIp(remoteAddressInfo.getIp());
            // 设备地址变化会引起目录订阅任务失效，需要重新添加
            if (device.getSubscribeCycleForCatalog() > 0) {
                deviceService.removeCatalogSubscribe(
                        device,
                        result -> {
                            deviceService.addCatalogSubscribe(device);
                        });
            }
        }
        if (device.getKeepaliveTime() == null) {
            device.setKeepaliveIntervalTime(60);
        } else {
            long lastTime = DateUtil.yyyy_MM_dd_HH_mm_ssToTimestamp(device.getKeepaliveTime());
            if (System.currentTimeMillis() / 1000 - lastTime > 10) {
                device.setKeepaliveIntervalTime(
                        Long.valueOf(System.currentTimeMillis() / 1000 - lastTime).intValue());
            }
        }

        device.setKeepaliveTime(DateUtil.getNow());

        if (device.isOnLine()) {
            deviceService.updateDevice(device);
        } else {
            // 对于已经离线的设备判断他的注册是否已经过期
            if (!deviceService.expire(device)) {
                device.setOnLine(false);
                deviceService.online(device, null);
            }
        }
        // 刷新过期任务
        String registerExpireTaskKey =
                VideoManagerConstants.REGISTER_EXPIRE_TASK_KEY_PREFIX + device.getDeviceId();
        // 如果三次心跳失败，则设置设备离线
        dynamicTask.startDelay(
                registerExpireTaskKey,
                () -> deviceService.offline(device.getDeviceId(), "三次心跳失败"),
                device.getKeepaliveIntervalTime() * 1000 * 3);
    }

    @Override
    public void handForPlatform(RequestEvent evt, ParentPlatform parentPlatform, Element element) {
        // 个别平台保活不回复200OK会判定离线
        try {
            responseAck((SIPRequest) evt.getRequest(), Response.OK);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            logger.error("[命令发送失败] 心跳回复: {}", e.getMessage());
        }
    }
}