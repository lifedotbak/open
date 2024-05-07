package com.spyker.framework.onvif;

import com.onvif.soap.OnvifDevice;
import com.spyker.framework.util.file.ExFileUtils;

import jakarta.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.onvif.ver10.schema.Profile;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/** jdk1.8测试通过，jdk17未通过，jdk1.8以后移除了rt.jar */
@Slf4j
public class OnvifUtils {

    String ip;
    String userName;
    String password;

    public OnvifUtils(String ip, String userName, String password) {
        this.ip = ip;
        this.userName = userName;
        this.password = password;
    }

    private static void doGetSnap(@NotNull String url, @NotNull String filePath) {

        Objects.requireNonNull(url, "url");
        Objects.requireNonNull(filePath, "filePath");

        try {

            File fout = new File(filePath);

            FileUtils.createParentDirectories(fout);
            ExFileUtils.copyURLToFile(new URL(url), fout);

        } catch (IOException e) {
            log.error("error-->{}", e);
        }
    }

    public void snapshot(@NotNull String token, @NotNull String filePath) throws Exception {

        Objects.requireNonNull(token, "token");
        Objects.requireNonNull(filePath, "filePath");

        String getSnapshotUri = getSnapshotUri(token);

        log.info("getSnapshotUri-->{}", getSnapshotUri);

        doGetSnap(getSnapshotUri, filePath);
    }

    /**
     * 获取指定的token
     *
     * @param index
     * @return
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
     * @throws Exception
     */
    public String getToken() throws Exception {
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

    public String getSnapshotUri(String token) throws Exception {
        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        return cam.getMedia().getSnapshotUri(token);
    }

    public String getRTSPStreamUri(String token) throws Exception {
        OnvifDevice cam = new OnvifDevice(ip, userName, password);

        String rtsp = cam.getMedia().getRTSPStreamUri(token);

        rtsp = rtsp.replace("rtsp://", "rtsp://" + userName + ":" + password + "@");

        return rtsp;
    }
}