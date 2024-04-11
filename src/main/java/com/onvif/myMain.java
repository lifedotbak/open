package com.onvif;

import com.onvif.soap.OnvifDevice;

import org.onvif.ver10.schema.Profile;

import java.net.ConnectException;
import java.util.List;

import javax.xml.soap.SOAPException;

public class myMain {

    private static final String INFO =
            "Commands:\n  \n  url: Get snapshort URL.\n  info: Get information about each valid command.\n  profiles: Get all profiles.\n  exit: Exit this application.";

    public static void main(String[] args) {

        String cameraAddress = "192.168.3.138";
        String user = "admin";
        String password = "123456";

        //        String cameraAddress = "192.168.15.88";
        //        String user = "admin";
        //        String password = "grid123456";

        // 实例化Onvif协议的设备信息
        // 这里会进行初始化包括：检查设备是否在线、准备Soap消息头消息体发送webService接口获取设备能力等信息
        OnvifDevice cam;
        try {
            cam = new OnvifDevice(cameraAddress, user, password);
        } catch (ConnectException | SOAPException e1) {
            System.err.println("No connection to camera, please try again.");
            return;
        }
        System.out.println("Connection to camera successful!");

        // 实例化后，调用api获取设备媒体信息（会存在主通道和子通道的区别，所以获取到的是个list）
        List<Profile> profiles = cam.getDevices().getProfiles();

        // 一般第一个是主通道
        if (0 < profiles.size()) {
            // 获取主通道的token信息 方便使用该token获取rtsp地址
            String token = profiles.get(0).getToken();
            try {

                System.out.println("token-->{}" + token);

                String rtspStreamUri = cam.getMedia().getRTSPStreamUri(token);

                String snapshoturi = cam.getMedia().getSnapshotUri(token);

                String getSceenshotUri = cam.getMedia().getSceenshotUri(token);

                System.out.println("snapshoturi-->{}" + snapshoturi);
                System.out.println("getSceenshotUri-->{}" + getSceenshotUri);

            } catch (ConnectException e) {
                e.printStackTrace();
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }
    }
}
