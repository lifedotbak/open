package com.spyker.commons.controller.common;

import com.spyker.framework.onvif.OnvifUtils;
import com.spyker.framework.response.RestResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 拉远
     *
     * @param ip
     * @param userName
     * @param password
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    @GetMapping("/zoom_out")
    public RestResponse<?> zoom_out(
            @RequestParam String ip, @RequestParam String userName, @RequestParam String password)
            throws SOAPException, ConnectException {

        OnvifUtils utils = new OnvifUtils(ip, userName, password);

        utils.zoom_out();

        return RestResponse.success();
    }

    /**
     * 拉近
     *
     * @param ip
     * @param userName
     * @param password
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    @GetMapping("/zoom_in")
    public RestResponse<?> zoom_in(
            @RequestParam String ip, @RequestParam String userName, @RequestParam String password)
            throws SOAPException, ConnectException {
        OnvifUtils utils = new OnvifUtils(ip, userName, password);

        utils.zoom_in();
        return RestResponse.success();
    }

    /**
     * 左装
     *
     * @param ip
     * @param userName
     * @param password
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    @GetMapping("/ptz_left")
    public RestResponse<?> ptz_left(
            @RequestParam String ip, @RequestParam String userName, @RequestParam String password)
            throws SOAPException, ConnectException {
        OnvifUtils utils = new OnvifUtils(ip, userName, password);

        utils.ptz_left();
        return RestResponse.success();
    }

    /**
     * 右转
     *
     * @param ip
     * @param userName
     * @param password
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    @GetMapping("/ptz_right")
    public RestResponse<?> ptz_right(
            @RequestParam String ip, @RequestParam String userName, @RequestParam String password)
            throws SOAPException, ConnectException {
        OnvifUtils utils = new OnvifUtils(ip, userName, password);

        utils.ptz_right();
        return RestResponse.success();
    }

    /**
     * 上转
     *
     * @param ip
     * @param userName
     * @param password
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    @GetMapping("/up")
    public RestResponse<?> up(
            @RequestParam String ip, @RequestParam String userName, @RequestParam String password)
            throws SOAPException, ConnectException {
        OnvifUtils utils = new OnvifUtils(ip, userName, password);

        utils.ptz_up();
        return RestResponse.success();
    }

    /**
     * 下转
     *
     * @param ip
     * @param userName
     * @param password
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    @GetMapping("/down")
    public RestResponse<?> down(
            @RequestParam String ip, @RequestParam String userName, @RequestParam String password)
            throws SOAPException, ConnectException {
        OnvifUtils utils = new OnvifUtils(ip, userName, password);

        utils.ptz_down();
        return RestResponse.success();
    }
}