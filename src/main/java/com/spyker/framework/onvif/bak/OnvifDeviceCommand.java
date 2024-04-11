package com.spyker.framework.onvif.bak;

import com.spyker.framework.onvif.bak.entity.OnvifDeviceExtend;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class OnvifDeviceCommand {

    @Autowired private RestTemplate restTemplate;

    /**
     * 获取服务能力
     *
     * @param onvifDevice
     * @return
     * @throws DocumentException
     */
    public void credential_get_service_capabilities(OnvifDeviceExtend onvifDevice)
            throws DocumentException {
        // 判断是否已获取token
        String token = token(onvifDevice);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();

        HttpEntity<String> formEntity =
                new HttpEntity<String>(
                        OnvifPackageXml.credential_get_service_capabilities(
                                onvifDevice.getOnvifUserName(), onvifDevice.getOnvifPassword()),
                        headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifDevice, formEntity);

        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);
    }

    /**
     * 获取设备回放能力
     *
     * @param onvifDevice
     * @return
     * @throws DocumentException
     */
    public boolean replay_get_service_capabilities(OnvifDeviceExtend onvifDevice)
            throws DocumentException {
        // 判断是否已获取token
        String token = token(onvifDevice);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();

        HttpEntity<String> formEntity =
                new HttpEntity<String>(
                        OnvifPackageXml.replay_get_service_capabilities(
                                onvifDevice.getOnvifUserName(), onvifDevice.getOnvifPassword()),
                        headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifDevice, formEntity);

        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        SAXReader reader = new SAXReader();
        Document document =
                reader.read(new ByteArrayInputStream(xmlStr.getBytes(StandardCharsets.UTF_8)));
        Element root = document.getRootElement();
        String xxx =
                root.element("Body")
                        .element("GetServiceCapabilitiesResponse")
                        .elements("Capabilities")
                        .get(0)
                        .attribute("ReversePlayback")
                        .getText();

        return !"false".equalsIgnoreCase(xxx);
    }

    public void getSnap(OnvifDeviceExtend onvifDevice, String path) {

        String url = mdeia_get_snap_shot_uri(onvifDevice);

        OnvifPackageXml.doGetSnap(url, path);
    }

    /**
     * 截图，返回照片的url
     *
     * @param onvifDevice
     * @return http://192.168.15.88/onvif-http/snapshot?Profile_101
     *     <p>http://admin:grid123456@192.168.15.88/onvif-http/snapshot?Profile_101
     */
    public String mdeia_get_snap_shot_uri(OnvifDeviceExtend onvifDevice) {

        // 判断是否已获取token
        String token = token(onvifDevice);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();

        HttpEntity<String> formEntity =
                new HttpEntity<String>(
                        OnvifPackageXml.mdeia_get_snap_shot_uri(
                                onvifDevice.getOnvifUserName(),
                                onvifDevice.getOnvifPassword(),
                                token),
                        headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifDevice, formEntity);

        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        String reg = "<tt:Uri>(.*?)\\</tt:Uri>";
        Pattern pattern = Pattern.compile(reg);
        // 内容 与 匹配规则 的测试
        Matcher matcher = pattern.matcher(xmlStr);
        if (matcher.find()) {
            // 包含前后的两个字符
            String rtspUrl = matcher.group(1);

            rtspUrl =
                    rtspUrl.replace(
                            "http://",
                            "http://"
                                    + onvifDevice.getOnvifUserName()
                                    + ":"
                                    + onvifDevice.getOnvifPassword()
                                    + "@");

            return rtspUrl;
        } else {
            // System.out.println(" 没有匹配到内容....");
            return null;
        }
    }

    /**
     * 获取视频流
     *
     * @param onvifDevice
     * @return
     *     rtsp://admin:grid123456@192.168.15.88:554/Streaming/Channels/101?transportmode=unicast&profile=Profile_101
     */
    public String stream(OnvifDeviceExtend onvifDevice) {

        // 判断是否已获取token
        String token = token(onvifDevice);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<String> formEntity =
                new HttpEntity<String>(
                        OnvifPackageXml.stream(
                                onvifDevice.getOnvifUserName(),
                                onvifDevice.getOnvifPassword(),
                                token),
                        headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifDevice, formEntity);

        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        String reg = "<tt:Uri>(.*?)\\</tt:Uri>";
        Pattern pattern = Pattern.compile(reg);
        // 内容 与 匹配规则 的测试
        Matcher matcher = pattern.matcher(xmlStr);
        if (matcher.find()) {
            // 包含前后的两个字符
            // System.out.println(matcher.group());
            // 不包含前后的两个字符
            String rtspUrl =
                    matcher.group(1)
                            .replace(
                                    "rtsp://",
                                    "rtsp://"
                                            + onvifDevice.getOnvifUserName()
                                            + ":"
                                            + onvifDevice.getOnvifPassword()
                                            + "@");
            return rtspUrl;
        } else {
            // System.out.println(" 没有匹配到内容....");
            return null;
        }
    }

    /**
     * 获取预置点位信息
     *
     * @param onvifDevice
     * @return
     */
    public Boolean ptz_get_presets(OnvifDeviceExtend onvifDevice) {

        // 判断是否已获取token
        String token = token(onvifDevice);
        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();
        String xml =
                OnvifPackageXml.ptz_get_presets(
                        onvifDevice.getOnvifUserName(), onvifDevice.getOnvifPassword(), token);

        log.info("xml-->{}", xml);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifDevice, formEntity);

        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);

        return true;
    }

    /**
     * 返回摄像头起始位置
     *
     * @param onvifDevice
     * @return
     */
    public Boolean ptz_goto_home(OnvifDeviceExtend onvifDevice) {

        // 判断是否已获取token
        String token = token(onvifDevice);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();
        String xml =
                OnvifPackageXml.ptz_goto_home(
                        onvifDevice.getOnvifUserName(), onvifDevice.getOnvifPassword(), token);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifDevice, formEntity);

        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        return true;
    }

    /**
     * 调用此方法前往预设点。
     *
     * @param onvifDevice
     * @param presetToken 预设点对应序号。
     * @return 布尔值，通常为true，表示操作成功。
     */
    public Boolean ptz_goto_preset(OnvifDeviceExtend onvifDevice, String presetToken) {

        // 判断是否已获取token
        String token = token(onvifDevice);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();

        String xml =
                OnvifPackageXml.ptz_goto_preset(
                        onvifDevice.getOnvifUserName(),
                        onvifDevice.getOnvifPassword(),
                        token,
                        presetToken);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifDevice, formEntity);
        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        return true;
    }

    public Boolean ptz_left(OnvifDeviceExtend onvifDevice) {
        return ptz(onvifDevice, 0.5, 0, 0);
    }

    public Boolean ptz_up(OnvifDeviceExtend onvifDevice) {
        return ptz(onvifDevice, 0, 0.5, 0);
    }

    public Boolean ptz_down(OnvifDeviceExtend onvifDevice) {
        return ptz(onvifDevice, 0, -0.5, 0);
    }

    public Boolean ptz_right(OnvifDeviceExtend onvifDevice) {
        return ptz(onvifDevice, -0.5, 0, 0);
    }

    public Boolean ptz_stop(OnvifDeviceExtend onvifDevice) {
        return ptz(onvifDevice, 0, 0, 0);
    }

    /**
     * 摄像头操控
     *
     * @param onvifDevice
     * @param leftRight
     * @param upDown
     * @param inOut
     * @return
     */
    private Boolean ptz(
            OnvifDeviceExtend onvifDevice, double leftRight, double upDown, double inOut) {

        if (leftRight > 1 && leftRight < -1) {
            return false;
        }
        if (upDown > 1 && upDown < -1) {
            return false;
        }
        if (inOut > 1 && inOut < -1) {
            return false;
        }

        // 判断是否已获取token
        String token = token(onvifDevice);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();
        String xml =
                OnvifPackageXml.ptz(
                        onvifDevice.getOnvifUserName(),
                        onvifDevice.getOnvifPassword(),
                        token,
                        leftRight,
                        upDown,
                        inOut);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifDevice, formEntity);
        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String result = StringEscapeUtils.unescapeXml(resultStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        return true;
    }

    /**
     * 获取token
     *
     * @param onvifDevice
     * @return token
     */
    public String token(OnvifDeviceExtend onvifDevice) {
        try {
            // 构造http请求头
            HttpHeaders headers = getHttpHeaders();

            String tokenXml =
                    OnvifPackageXml.token(
                            onvifDevice.getOnvifUserName(), onvifDevice.getOnvifPassword());
            HttpEntity<String> formEntity = new HttpEntity<String>(tokenXml, headers);

            // 返回结果
            String resultStr = getSoapResponse(onvifDevice, formEntity);

            // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转义
            String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

            log.error("token xmlStr - >{}", xmlStr);

            SAXReader reader = new SAXReader();
            Document document =
                    reader.read(new ByteArrayInputStream(xmlStr.getBytes(StandardCharsets.UTF_8)));
            Element root = document.getRootElement();
            String token =
                    root.element("Body")
                            .element("GetProfilesResponse")
                            .elements("Profiles")
                            .get(0)
                            .attribute("token")
                            .getText();

            return token;
        } catch (RestClientException | DocumentException e) {

            log.error("error - >{}", e);
            return null;
        }
    }

    /**
     * 请求报文，并获取相应
     *
     * @param onvifDevice
     * @param formEntity
     * @return
     */
    @Nullable
    private String getSoapResponse(OnvifDeviceExtend onvifDevice, HttpEntity<String> formEntity) {

        String url =
                "http://"
                        + onvifDevice.getIp()
                        + ":"
                        + onvifDevice.getOnvifPort()
                        + "/onvif/device_service";

        String resultStr = restTemplate.postForObject(url, formEntity, String.class);

        return resultStr;
    }

    /**
     * 构造http请求头
     *
     * @return HttpHeaders
     */
    @NotNull
    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
        headers.setContentType(type);
        return headers;
    }
}