package com.spyker.framework.onvif;

import com.onvif.soap.OnvifDevice;
import com.onvif.soap.devices.PtzDevices;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.onvif.ver10.schema.Profile;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.List;

import javax.xml.soap.SOAPException;

/** jdk1.8测试通过，jdk17未通过，jdk1.8以后移除了rt.jar */
@Slf4j
public class OnvifUtils {

    String ip;
    String userName;
    String password;

    long continuousTime = 500;
    float ptzSpeed = 0.2f;

    public OnvifUtils(String ip, String userName, String password) {
        this.ip = ip;
        this.userName = userName;
        this.password = password;
    }

    public void gotoHome() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.absoluteMove(token, 1f, 1f, 1f);
    }

    public void gotoPreset(String presetToken) throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.gotoPreset(presetToken, token);
    }

    public void ptz(float x, float y) throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, x, y, 0);

        try {
            Thread.sleep(continuousTime);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        ptzDevices.stopMove(token);
    }

    public void ptz_c(float x, float y) throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, x, y, 0);
    }

    public void stop() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.stopMove(token);
    }

    /**
     * 镜头拉近
     *
     * @throws SOAPException
     * @throws ConnectException
     */
    public void zoom_in() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, 0, 0, 0.5f);

        try {
            Thread.sleep(continuousTime);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        ptzDevices.stopMove(token);
    }

    public void zoom_in_c() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, 0, 0, 0.5f);
    }

    /**
     * 镜头拉远
     *
     * @throws SOAPException
     * @throws ConnectException
     */
    public void zoom_out() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, 0, 0, -0.5f);

        try {
            Thread.sleep(continuousTime);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        ptzDevices.stopMove(token);
    }

    public void zoom_out_c() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, 0, 0, -0.5f);
    }

    /**
     * 左转
     *
     * @throws SOAPException
     * @throws ConnectException
     */
    public void ptz_left() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, 0.5f, 0, 0);

        try {
            Thread.sleep(continuousTime);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        ptzDevices.stopMove(token);
    }

    public void ptz_left_c() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, 0.5f, 0, 0);
    }

    /**
     * 上
     *
     * @throws SOAPException
     * @throws ConnectException
     */
    public void ptz_up() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, 0, 0.5f, 0);

        try {
            Thread.sleep(continuousTime);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        ptzDevices.stopMove(token);
    }

    public void ptz_up_c() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, 0, 0.5f, 0);
    }

    /**
     * 下
     *
     * @throws SOAPException
     * @throws ConnectException
     */
    public void ptz_down() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, 0, -0.5f, 0);

        try {
            Thread.sleep(continuousTime);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        ptzDevices.stopMove(token);
    }

    public void ptz_down_c() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, 0, -0.5f, 0);
    }

    /**
     * 右转
     *
     * @throws SOAPException
     * @throws ConnectException
     */
    public void ptz_right() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, -0.5f, 0, 0);

        try {
            Thread.sleep(continuousTime);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }

        ptzDevices.stopMove(token);
    }

    public void ptz_right_c() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.continuousMove(token, -0.5f, 0, 0);
    }

    public void snapshot(String token, String filePath) throws Exception {

        String getSnapshotUri = getSnapshotUri(token);

        log.info("getSnapshotUri-->{}", getSnapshotUri);

        if (null != getSnapshotUri) {

            try {

                File fout = new File(filePath);

                FileUtils.createParentDirectories(fout);

                FileUtils.copyURLToFile(new URL(getSnapshotUri), fout);
            } catch (IOException e) {
                log.error("error-->{}", e);
            }
        }
    }

    /**
     * 获取指定的token
     *
     * @param index
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    public String getToken(int index) throws Exception {
        String token = "";

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();

        if (profiles.size() >= index) {

            token = profiles.get(index - 1).getToken();
        }

        return token;
    }

    /**
     * 获取token
     *
     * @return
     * @throws SOAPException
     * @throws ConnectException
     */
    public String getToken() throws SOAPException, ConnectException {
        String token = "";

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        return token;
    }

    public String getRTSPStreamUri(String token) throws Exception {
        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String rtsp = cam.getMedia().getRTSPStreamUri(token);

        rtsp = rtsp.replace("rtsp://", "rtsp://" + userName + ":" + password + "@");

        return rtsp;
    }

    public String getTcpStreamUri(String token) throws Exception {
        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String tcp = cam.getMedia().getTCPStreamUri(token);

        return tcp;
    }

    public String getSnapshotUri(String token) throws Exception {
        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        return cam.getMedia().getSnapshotUri(token);
    }

    public void ptz_stop() throws SOAPException, ConnectException {

        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String token = "";

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();
        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            token = profiles.get(0).getToken();
        }

        PtzDevices ptzDevices = cam.getPtz();

        ptzDevices.stopMove(token);
    }
}