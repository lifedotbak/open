package com.spyker.framework.onvif;

import com.onvif.soap.OnvifDevice;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.onvif.ver10.schema.Profile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URI;
import java.util.List;

import javax.imageio.ImageIO;

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

    private static void doGetSnap(String url, String filePath) {

        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder(url);

            URI uri = builder.build();

            // 创建GET请求
            HttpGet httpGet = new HttpGet(uri);
            //            httpGet.addHeader("Authorization", "Basic " + encoding);
            // 发送请求
            response = httpClient.execute(httpGet);
            // 判断响应状态
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream result = response.getEntity().getContent();

                File fout = new File(filePath);

                FileUtils.createParentDirectories(fout);

                ImageIO.write(ImageIO.read(result), "jpg", fout);
            }
        } catch (Exception e) {
            log.error("error-->{}", e);
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                log.error("error-->{}", e);
            }
        }
    }

    public void snapshot(String token, String filePath) throws Exception {

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
}