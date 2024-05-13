package com.spyker.commons.controller.common;

import com.spyker.framework.onvif.OnvifUtils;
import com.spyker.framework.onvif.ver10.entity.OnvifDeviceExtend;
import com.spyker.framework.response.RestResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.ConnectException;

import javax.xml.soap.SOAPException;

/**
 * ONVIF摄像头控制
 *
 * @author CodeGenerator
 * @since 2024-04-25
 */
@RestController
@RequestMapping("/onvif")
public class OnvifController {

    /**
     * 双方向持续控制
     *
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    @GetMapping("/ptz_xy_c")
    public RestResponse<?> ptz_xy_c(OnvifDeviceExtend onvifDeviceExtend, String op)
            throws SOAPException, ConnectException {

        if (null != onvifDeviceExtend) {
            OnvifUtils utils =
                    new OnvifUtils(
                            onvifDeviceExtend.ip(),
                            onvifDeviceExtend.onvifUserName(),
                            onvifDeviceExtend.onvifPassword());

            if ("xy".equalsIgnoreCase(op)) {
                utils.ptz_c(0.5f, 0.5f);
            }

            if ("x-y".equalsIgnoreCase(op)) {
                utils.ptz_c(0.5f, -0.5f);
            }

            if ("-xy".equalsIgnoreCase(op)) {
                utils.ptz_c(-0.5f, 0.5f);
            }

            if ("-x-y".equalsIgnoreCase(op)) {
                utils.ptz_c(-0.5f, -0.5f);
            }
        }

        return RestResponse.success();
    }

    /**
     * 双方向
     *
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    @GetMapping("/ptz_xy")
    public RestResponse<?> ptz_xy(OnvifDeviceExtend onvifDeviceExtend, String op)
            throws SOAPException, ConnectException {

        if (null != onvifDeviceExtend) {
            OnvifUtils utils =
                    new OnvifUtils(
                            onvifDeviceExtend.ip(),
                            onvifDeviceExtend.onvifUserName(),
                            onvifDeviceExtend.onvifPassword());

            /** 右上 */
            if ("xy".equalsIgnoreCase(op)) {
                utils.ptz(0.5f, 0.5f);
            }

            /** 右下 */
            if ("x-y".equalsIgnoreCase(op)) {
                utils.ptz(0.5f, -0.5f);
            }

            /** 左上 */
            if ("-xy".equalsIgnoreCase(op)) {
                utils.ptz(-0.5f, 0.5f);
            }

            /** 左下 */
            if ("-x-y".equalsIgnoreCase(op)) {
                utils.ptz(-0.5f, -0.5f);
            }
        }

        return RestResponse.success();
    }

    /**
     * 上下左右，放大缩小
     *
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    @GetMapping("/ptz")
    public RestResponse<?> ptz(OnvifDeviceExtend onvifDeviceExtend, String op)
            throws SOAPException, ConnectException {

        if (null != onvifDeviceExtend) {
            OnvifUtils utils =
                    new OnvifUtils(
                            onvifDeviceExtend.ip(),
                            onvifDeviceExtend.onvifUserName(),
                            onvifDeviceExtend.onvifPassword());

            if ("zoom_out".equalsIgnoreCase(op)) {
                utils.zoom_out();
            }

            if ("zoom_in".equalsIgnoreCase(op)) {
                utils.zoom_in();
            }

            if ("ptz_left".equalsIgnoreCase(op)) {
                utils.ptz_left();
            }

            if ("ptz_right".equalsIgnoreCase(op)) {
                utils.ptz_right();
            }

            if ("ptz_up".equalsIgnoreCase(op)) {
                utils.ptz_up();
            }

            if ("ptz_down".equalsIgnoreCase(op)) {
                utils.ptz_down();
            }
        }

        return RestResponse.success();
    }

    /**
     * 持续上下左右，放大缩小
     *
     * @throws SOAPException
     * @throws ConnectException
     */
    @GetMapping("/ptz_c")
    public RestResponse<?> ptz_c(OnvifDeviceExtend onvifDeviceExtend, String op)
            throws SOAPException, ConnectException {

        if (null != onvifDeviceExtend) {
            OnvifUtils utils =
                    new OnvifUtils(
                            onvifDeviceExtend.ip(),
                            onvifDeviceExtend.onvifUserName(),
                            onvifDeviceExtend.onvifPassword());

            if ("zoom_out".equalsIgnoreCase(op)) {
                utils.zoom_out_c();
            }

            if ("zoom_in".equalsIgnoreCase(op)) {
                utils.zoom_in_c();
            }

            if ("ptz_left".equalsIgnoreCase(op)) {
                utils.ptz_left_c();
            }

            if ("ptz_right".equalsIgnoreCase(op)) {
                utils.ptz_right_c();
            }

            if ("ptz_up".equalsIgnoreCase(op)) {
                utils.ptz_up_c();
            }

            if ("ptz_down".equalsIgnoreCase(op)) {
                utils.ptz_down_c();
            }
        }

        return RestResponse.success();
    }

    @GetMapping("/stop")
    public RestResponse<?> stop(OnvifDeviceExtend onvifDeviceExtend)
            throws SOAPException, ConnectException {

        if (null != onvifDeviceExtend) {
            OnvifUtils utils =
                    new OnvifUtils(
                            onvifDeviceExtend.ip(),
                            onvifDeviceExtend.onvifUserName(),
                            onvifDeviceExtend.onvifPassword());

            utils.ptz_left();
        }
        return RestResponse.success();
    }
}