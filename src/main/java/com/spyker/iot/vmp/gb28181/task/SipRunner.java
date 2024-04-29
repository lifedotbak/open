package com.spyker.iot.vmp.gb28181.task;

import com.spyker.iot.vmp.conf.UserSetting;
import com.spyker.iot.vmp.gb28181.bean.Device;
import com.spyker.iot.vmp.gb28181.bean.ParentPlatform;
import com.spyker.iot.vmp.gb28181.bean.SendRtpItem;
import com.spyker.iot.vmp.gb28181.session.SSRCFactory;
import com.spyker.iot.vmp.gb28181.transmit.cmd.ISIPCommanderForPlatform;
import com.spyker.iot.vmp.media.bean.MediaServer;
import com.spyker.iot.vmp.media.service.IMediaServerService;
import com.spyker.iot.vmp.service.IDeviceService;
import com.spyker.iot.vmp.service.IPlatformService;
import com.spyker.iot.vmp.service.impl.PlatformServiceImpl;
import com.spyker.iot.vmp.storager.IRedisCatchStorage;
import com.spyker.iot.vmp.storager.IVideoManagerStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;

/**
 * 系统启动时控制设备
 *
 * @author lin
 */
@Component
@Order(value = 14)
public class SipRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);
    @Autowired private IVideoManagerStorage storager;
    @Autowired private IRedisCatchStorage redisCatchStorage;
    @Autowired private SSRCFactory ssrcFactory;
    @Autowired private UserSetting userSetting;
    @Autowired private IDeviceService deviceService;
    @Autowired private IMediaServerService mediaServerService;
    @Autowired private IPlatformService platformService;
    @Autowired private ISIPCommanderForPlatform commanderForPlatform;

    @Override
    public void run(String... args) throws Exception {
        List<Device> deviceList = deviceService.getAllOnlineDevice();

        for (Device device : deviceList) {
            if (deviceService.expire(device)) {
                deviceService.offline(device.getDeviceId(), "注册已过期");
            } else {
                deviceService.online(device, null);
            }
        }
        // 重置cseq计数
        redisCatchStorage.resetAllCSEQ();
        // 清理redis
        // 清理数据库不存在但是redis中存在的数据
        List<Device> devicesInDb = deviceService.getAll();
        if (devicesInDb.size() == 0) {
            redisCatchStorage.removeAllDevice();
        } else {
            List<Device> devicesInRedis = redisCatchStorage.getAllDevices();
            if (devicesInRedis.size() > 0) {
                Map<String, Device> deviceMapInDb = new HashMap<>();
                devicesInDb.parallelStream()
                        .forEach(
                                device -> {
                                    deviceMapInDb.put(device.getDeviceId(), device);
                                });
                devicesInRedis.parallelStream()
                        .forEach(
                                device -> {
                                    if (deviceMapInDb.get(device.getDeviceId()) == null) {
                                        redisCatchStorage.removeDevice(device.getDeviceId());
                                    }
                                });
            }
        }

        // 查找国标推流
        List<SendRtpItem> sendRtpItems = redisCatchStorage.queryAllSendRTPServer();
        if (sendRtpItems.size() > 0) {
            for (SendRtpItem sendRtpItem : sendRtpItems) {
                MediaServer mediaServerItem =
                        mediaServerService.getOne(sendRtpItem.getMediaServerId());
                redisCatchStorage.deleteSendRTPServer(
                        sendRtpItem.getPlatformId(),
                        sendRtpItem.getChannelId(),
                        sendRtpItem.getCallId(),
                        sendRtpItem.getStream());
                if (mediaServerItem != null) {
                    ssrcFactory.releaseSsrc(sendRtpItem.getMediaServerId(), sendRtpItem.getSsrc());
                    boolean stopResult =
                            mediaServerService.stopSendRtp(
                                    mediaServerItem,
                                    sendRtpItem.getApp(),
                                    sendRtpItem.getStream(),
                                    sendRtpItem.getSsrc());
                    if (stopResult) {
                        ParentPlatform platform =
                                platformService.queryPlatformByServerGBId(
                                        sendRtpItem.getPlatformId());
                        if (platform != null) {
                            try {
                                commanderForPlatform.streamByeCmd(
                                        platform, sendRtpItem.getCallId());
                            } catch (InvalidArgumentException | ParseException | SipException e) {
                                logger.error("[命令发送失败] 国标级联 发送BYE: {}", e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }
}