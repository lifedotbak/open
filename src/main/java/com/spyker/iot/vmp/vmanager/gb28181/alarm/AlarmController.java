package com.spyker.iot.vmp.vmanager.gb28181.alarm;

import com.spyker.iot.vmp.conf.exception.ControllerException;
import com.spyker.iot.vmp.gb28181.bean.Device;
import com.spyker.iot.vmp.gb28181.bean.DeviceAlarm;
import com.spyker.iot.vmp.gb28181.bean.ParentPlatform;
import com.spyker.iot.vmp.gb28181.transmit.cmd.ISIPCommander;
import com.spyker.iot.vmp.gb28181.transmit.cmd.ISIPCommanderForPlatform;
import com.spyker.iot.vmp.service.IDeviceAlarmService;
import com.spyker.iot.vmp.storager.IVideoManagerStorage;
import com.spyker.iot.vmp.utils.DateUtil;
import com.spyker.iot.vmp.vmanager.bean.ErrorCode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;

@Tag(name = "报警信息管理")
@RestController
@RequestMapping("/api/alarm")
public class AlarmController {

    private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @Autowired private IDeviceAlarmService deviceAlarmService;

    @Autowired private ISIPCommander commander;

    @Autowired private ISIPCommanderForPlatform commanderForPlatform;

    @Autowired private IVideoManagerStorage storage;

    /**
     * 删除报警
     *
     * @param id 报警id
     * @param deviceIds 多个设备id,逗号分隔
     * @param time 结束时间(这个时间之前的报警会被删除)
     * @return
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除报警")
    @Parameter(name = "id", description = "ID")
    @Parameter(name = "deviceIds", description = "多个设备id,逗号分隔")
    @Parameter(name = "time", description = "结束时间")
    public Integer delete(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String deviceIds,
            @RequestParam(required = false) String time) {
        if (ObjectUtils.isEmpty(id)) {
            id = null;
        }
        if (ObjectUtils.isEmpty(deviceIds)) {
            deviceIds = null;
        }

        if (ObjectUtils.isEmpty(time)) {
            time = null;
        } else if (!DateUtil.verification(time, DateUtil.formatter)) {
            throw new ControllerException(
                    ErrorCode.ERROR400.getCode(), "time格式为" + DateUtil.PATTERN);
        }
        List<String> deviceIdList = null;
        if (deviceIds != null) {
            String[] deviceIdArray = deviceIds.split(",");
            deviceIdList = Arrays.asList(deviceIdArray);
        }

        return deviceAlarmService.clearAlarmBeforeTime(id, deviceIdList, time);
    }

    /**
     * 测试向上级/设备发送模拟报警通知
     *
     * @param deviceId 报警id
     * @return
     */
    @GetMapping("/test/notify/alarm")
    @Operation(summary = "测试向上级/设备发送模拟报警通知")
    @Parameter(name = "deviceId", description = "设备国标编号")
    public void delete(@RequestParam String deviceId) {
        Device device = storage.queryVideoDevice(deviceId);
        ParentPlatform platform = storage.queryParentPlatByServerGBId(deviceId);
        DeviceAlarm deviceAlarm = new DeviceAlarm();
        deviceAlarm.setChannelId(deviceId);
        deviceAlarm.setAlarmDescription("test");
        deviceAlarm.setAlarmMethod("1");
        deviceAlarm.setAlarmPriority("1");
        deviceAlarm.setAlarmTime(DateUtil.getNow());
        deviceAlarm.setAlarmType("1");
        deviceAlarm.setLongitude(115.33333);
        deviceAlarm.setLatitude(39.33333);

        if (device != null && platform == null) {

            try {
                commander.sendAlarmMessage(device, deviceAlarm);
            } catch (InvalidArgumentException | SipException | ParseException e) {

            }
        } else if (device == null && platform != null) {
            try {
                commanderForPlatform.sendAlarmMessage(platform, deviceAlarm);
            } catch (SipException | InvalidArgumentException | ParseException e) {
                logger.error("[命令发送失败] 国标级联 发送BYE: {}", e.getMessage());
                throw new ControllerException(
                        ErrorCode.ERROR100.getCode(), "命令发送失败: " + e.getMessage());
            }
        } else {
            throw new ControllerException(
                    ErrorCode.ERROR100.getCode(), "无法确定" + deviceId + "是平台还是设备");
        }
    }
}