package com.spyker.framework.onvif;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class SendOnvifCmd {

    @Autowired private RestTemplate restTemplate;

    public String stream(String onvifUserName, String onvifPassword, String ip, int onvifPort) {

        // 判断是否已获取token
        String token = getToken(onvifUserName, onvifPassword, ip, onvifPort);

        log.info("token-->{}", token);

        // 构造http请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> formEntity =
                new HttpEntity<String>(
                        PackageXml.streamUri(onvifUserName, onvifPassword, token), headers);
        // 返回结果
        String resultStr =
                restTemplate.postForObject(
                        "http://" + ip + ":" + onvifPort + "/onvif/device_service",
                        formEntity,
                        String.class);
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
                                    "rtsp://" + onvifUserName + ":" + onvifPassword + "@");
            return rtspUrl;
        } else {
            // System.out.println(" 没有匹配到内容....");
            return null;
        }
    }

    public Boolean getPresets(
            String onvifUserName, String onvifPassword, String ip, int onvifPort) {

        // 判断是否已获取token
        String token = getToken(onvifUserName, onvifPassword, ip, onvifPort);
        // 构造http请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
        headers.setContentType(type);
        String xml = PackageXml.getPresets(onvifUserName, onvifPassword, token);

        log.info("xml-->{}", xml);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr =
                restTemplate.postForObject(
                        "http://" + ip + ":" + onvifPort + "/onvif/device_service",
                        formEntity,
                        String.class);
        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        log.info("xmlStr-->{}", xmlStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        return true;
    }

    public Boolean gotoHome(String onvifUserName, String onvifPassword, String ip, int onvifPort) {

        // 判断是否已获取token
        String token = getToken(onvifUserName, onvifPassword, ip, onvifPort);
        // 构造http请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
        headers.setContentType(type);
        String xml = PackageXml.gotoHome(onvifUserName, onvifPassword, token);

        log.info("xml-->{}", xml);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr =
                restTemplate.postForObject(
                        "http://" + ip + ":" + onvifPort + "/onvif/device_service",
                        formEntity,
                        String.class);
        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        log.info("xmlStr-->{}", xmlStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        return true;
    }

    public Boolean gotoPreset(
            String onvifUserName,
            String onvifPassword,
            String ip,
            int onvifPort,
            String presetName) {

        // 判断是否已获取token
        String token = getToken(onvifUserName, onvifPassword, ip, onvifPort);
        // 构造http请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
        headers.setContentType(type);
        String xml = PackageXml.gotoPreset(onvifUserName, onvifPassword, token, presetName);

        log.info("xml-->{}", xml);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr =
                restTemplate.postForObject(
                        "http://" + ip + ":" + onvifPort + "/onvif/device_service",
                        formEntity,
                        String.class);
        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String xmlStr = StringEscapeUtils.unescapeXml(resultStr);

        log.info("xmlStr-->{}", xmlStr);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        return true;
    }

    public Boolean left(String onvifUserName, String onvifPassword, String ip, int onvifPort) {
        return ptz(onvifUserName, onvifPassword, ip, 80, 0.5, 0, 0);
    }

    public Boolean up(String onvifUserName, String onvifPassword, String ip, int onvifPort) {
        return ptz(onvifUserName, onvifPassword, ip, 80, 0, 0.5, 0);
    }

    public Boolean down(String onvifUserName, String onvifPassword, String ip, int onvifPort) {
        return ptz(onvifUserName, onvifPassword, ip, 80, 0, -0.5, 0);
    }

    public Boolean right(String onvifUserName, String onvifPassword, String ip, int onvifPort) {
        return ptz(onvifUserName, onvifPassword, ip, 80, -0.5, 0, 0);
    }

    public Boolean stop(String onvifUserName, String onvifPassword, String ip, int onvifPort) {
        return ptz(onvifUserName, onvifPassword, ip, 80, 0, 0, 0);
    }

    private Boolean ptz(
            String onvifUserName,
            String onvifPassword,
            String ip,
            int onvifPort,
            double leftRight,
            double upDown,
            double inOut) {

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
        String token = getToken(onvifUserName, onvifPassword, ip, onvifPort);
        // 构造http请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
        headers.setContentType(type);
        String xml = PackageXml.ptz(onvifUserName, onvifPassword, token, leftRight, upDown, inOut);
        log.info("ptz xml-->{}", xml);

        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
        // 返回结果
        String resultStr =
                restTemplate.postForObject(
                        "http://" + ip + ":" + onvifPort + "/onvif/device_service",
                        formEntity,
                        String.class);
        // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
        String result = StringEscapeUtils.unescapeXml(resultStr);
        log.info("result-->{}", result);

        // JSONObject obj = XML.toJSONObject(tmpStr);
        return true;
    }

    private String getToken(String onvifUserName, String onvifPassword, String ip, int onvifPort) {
        try {
            // 构造http请求头
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
            headers.setContentType(type);
            String tokenXml = PackageXml.token(onvifUserName, onvifPassword);
            HttpEntity<String> formEntity = new HttpEntity<String>(tokenXml, headers);
            // 返回结果
            String resultStr =
                    restTemplate.postForObject(
                            "http://" + ip + ":" + onvifPort + "/onvif/device_service",
                            formEntity,
                            String.class);
            // 转换返回结果中的特殊字符，返回的结果中会将xml转义，此处需要反转移
            String xmlStr = StringEscapeUtils.unescapeXml(resultStr);
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
}