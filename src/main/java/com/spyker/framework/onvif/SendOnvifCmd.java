package com.spyker.framework.onvif;

import com.spyker.framework.onvif.entity.OnvifLoginInfo;

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
public class SendOnvifCmd {

    @Autowired private RestTemplate restTemplate;

    /**
     * 截图，返回照片的url
     *
     * @param onvifLoginInfo
     * @return http://192.168.15.88/onvif-http/snapshot?Profile_101
     *     <p>http://admin:grid123456@192.168.15.88/onvif-http/snapshot?Profile_101
     */
    public String getSnapShotUri(OnvifLoginInfo onvifLoginInfo) {

        // 判断是否已获取token
        String token = getToken(onvifLoginInfo);
        log.info("token-->{}", token);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();

        HttpEntity<String> formEntity =
                new HttpEntity<String>(
                        PackageXml.getSnaoShotUri(
                                onvifLoginInfo.getOnvifUserName(),
                                onvifLoginInfo.getOnvifPassword(),
                                token),
                        headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifLoginInfo, formEntity);

        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        log.info("xmlStr-->{}", xmlStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        String reg = "<tt:Uri>(.*?)\\</tt:Uri>";
        Pattern pattern = Pattern.compile(reg);
        // 内容 与 匹配规则 的测试
        Matcher matcher = pattern.matcher(xmlStr);
        if (matcher.find()) {
            // 包含前后的两个字符
            String rtspUrl = matcher.group(1);
            return rtspUrl;
        } else {
            // System.out.println(" 没有匹配到内容....");
            return null;
        }
    }

    /**
     * 获取视频流
     *
     * @param onvifLoginInfo
     * @return
     *     rtsp://admin:grid123456@192.168.15.88:554/Streaming/Channels/101?transportmode=unicast&profile=Profile_101
     */
    public String getStreamUri(OnvifLoginInfo onvifLoginInfo) {

        // 判断是否已获取token
        String token = getToken(onvifLoginInfo);

        log.info("token-->{}", token);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<String> formEntity =
                new HttpEntity<String>(
                        PackageXml.streamUri(
                                onvifLoginInfo.getOnvifUserName(),
                                onvifLoginInfo.getOnvifPassword(),
                                token),
                        headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifLoginInfo, formEntity);

        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        log.info("xmlStr-->{}", xmlStr);

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
                                            + onvifLoginInfo.getOnvifUserName()
                                            + ":"
                                            + onvifLoginInfo.getOnvifPassword()
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
     * @param onvifLoginInfo
     * @return
     */
    public Boolean getPresets(OnvifLoginInfo onvifLoginInfo) {

        // 判断是否已获取token
        String token = getToken(onvifLoginInfo);
        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();
        String xml =
                PackageXml.getPresets(
                        onvifLoginInfo.getOnvifUserName(),
                        onvifLoginInfo.getOnvifPassword(),
                        token);

        log.info("xml-->{}", xml);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifLoginInfo, formEntity);

        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        log.info("xmlStr-->{}", xmlStr);
        // JSONObject obj = XML.toJSONObject(tmpStr);

        return true;
    }

    /**
     * 返回摄像头起始位置
     *
     * @param onvifLoginInfo
     * @return
     */
    public Boolean gotoHome(OnvifLoginInfo onvifLoginInfo) {

        // 判断是否已获取token
        String token = getToken(onvifLoginInfo);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();
        String xml =
                PackageXml.gotoHome(
                        onvifLoginInfo.getOnvifUserName(),
                        onvifLoginInfo.getOnvifPassword(),
                        token);

        log.info("xml-->{}", xml);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifLoginInfo, formEntity);

        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        log.info("xmlStr-->{}", xmlStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        return true;
    }

    /**
     * 调用此方法前往预设点。
     *
     * @param onvifLoginInfo
     * @param presetToken 预设点对应序号。
     * @return 布尔值，通常为true，表示操作成功。
     */
    public Boolean gotoPreset(OnvifLoginInfo onvifLoginInfo, String presetToken) {

        // 判断是否已获取token
        String token = getToken(onvifLoginInfo);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();

        String xml =
                PackageXml.gotoPreset(
                        onvifLoginInfo.getOnvifUserName(),
                        onvifLoginInfo.getOnvifPassword(),
                        token,
                        presetToken);

        log.info("xml-->{}", xml);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifLoginInfo, formEntity);
        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        log.info("xmlStr-->{}", xmlStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        return true;
    }

    public Boolean left(OnvifLoginInfo onvifLoginInfo) {
        return ptz(onvifLoginInfo, 0.5, 0, 0);
    }

    public Boolean up(OnvifLoginInfo onvifLoginInfo) {
        return ptz(onvifLoginInfo, 0, 0.5, 0);
    }

    public Boolean down(OnvifLoginInfo onvifLoginInfo) {
        return ptz(onvifLoginInfo, 0, -0.5, 0);
    }

    public Boolean right(OnvifLoginInfo onvifLoginInfo) {
        return ptz(onvifLoginInfo, -0.5, 0, 0);
    }

    public Boolean stop(OnvifLoginInfo onvifLoginInfo) {
        return ptz(onvifLoginInfo, 0, 0, 0);
    }

    /**
     * 摄像头操控
     *
     * @param onvifLoginInfo
     * @param leftRight
     * @param upDown
     * @param inOut
     * @return
     */
    private Boolean ptz(
            OnvifLoginInfo onvifLoginInfo, double leftRight, double upDown, double inOut) {

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
        String token = getToken(onvifLoginInfo);

        // 构造http请求头
        HttpHeaders headers = getHttpHeaders();
        String xml =
                PackageXml.ptz(
                        onvifLoginInfo.getOnvifUserName(),
                        onvifLoginInfo.getOnvifPassword(),
                        token,
                        leftRight,
                        upDown,
                        inOut);
        log.info("ptz xml-->{}", xml);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr = getSoapResponse(onvifLoginInfo, formEntity);
        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String result = StringEscapeUtils.unescapeXml(resultStr);
        log.info("result-->{}", result);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        return true;
    }

    /**
     * 获取token
     *
     * @param onvifLoginInfo
     * @return token
     */
    public String getToken(OnvifLoginInfo onvifLoginInfo) {
        try {
            // 构造http请求头
            HttpHeaders headers = getHttpHeaders();

            String tokenXml =
                    PackageXml.token(
                            onvifLoginInfo.getOnvifUserName(), onvifLoginInfo.getOnvifPassword());
            HttpEntity<String> formEntity = new HttpEntity<String>(tokenXml, headers);

            // 返回结果
            String resultStr = getSoapResponse(onvifLoginInfo, formEntity);

            // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
            String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

            log.error("xmlStr - >{}", xmlStr);

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
     * @param onvifLoginInfo
     * @param formEntity
     * @return
     */
    @Nullable
    private String getSoapResponse(OnvifLoginInfo onvifLoginInfo, HttpEntity<String> formEntity) {

        String resultStr =
                restTemplate.postForObject(
                        "http://"
                                + onvifLoginInfo.getIp()
                                + ":"
                                + onvifLoginInfo.getOnvifPort()
                                + "/onvif/device_service",
                        formEntity,
                        String.class);
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