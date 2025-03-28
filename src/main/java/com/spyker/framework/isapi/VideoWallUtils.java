package com.spyker.framework.isapi;

import com.google.gson.Gson;
import com.spyker.framework.isapi.convert.StreamInputChannelXmlConvert;
import com.spyker.framework.isapi.convert.SubWindowParamPutXmlConvert;
import com.spyker.framework.isapi.convert.WallOutputListXmlConvert;
import com.spyker.framework.isapi.convert.WallScaleJsonConvert;
import com.spyker.framework.isapi.convert.WallWindowXmlConvert;
import com.spyker.framework.isapi.entity.DecoderLoginInfo;
import com.spyker.framework.isapi.message.EncodeDevInfo;
import com.spyker.framework.isapi.message.MediaGatewayInfo;
import com.spyker.framework.isapi.message.OutputResolution;
import com.spyker.framework.isapi.message.PortInBoard;
import com.spyker.framework.isapi.message.StreamByDomain;
import com.spyker.framework.isapi.message.StreamInput;
import com.spyker.framework.isapi.message.StreamInputChannel;
import com.spyker.framework.isapi.message.StreamInputRealtime;
import com.spyker.framework.isapi.message.StreamRealtimeUnit;
import com.spyker.framework.isapi.message.VideoOutputChannel;
import com.spyker.framework.isapi.message.VideoWall;
import com.spyker.framework.isapi.message.VideoWallCap;
import com.spyker.framework.isapi.message.VideoWallScale;
import com.spyker.framework.isapi.message.VideoWallScaleList;
import com.spyker.framework.isapi.xmlparse.CoordinateXml;
import com.spyker.framework.isapi.xmlparse.HikDecoderResponseStatus;
import com.spyker.framework.isapi.xmlparse.RectXml;
import com.spyker.framework.isapi.xmlparse.WallOutputXml;
import com.spyker.framework.isapi.xmlparse.wall.RunningScene;
import com.spyker.framework.isapi.xmlparse.wall.SubWindow;
import com.spyker.framework.isapi.xmlparse.wall.SubWindowParam;
import com.spyker.framework.isapi.xmlparse.wall.WallScene;
import com.spyker.framework.isapi.xmlparse.wall.WallWindow;
import com.thoughtworks.xstream.io.xml.DomDriver;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class VideoWallUtils {

    private static final String XML_HEARD = "<?xml version='1.0' encoding='UTF-8'?>";

    public static void main(String[] args) throws Exception {

        String ip = "192.168.200.155";
        short port = 80;
        String userName = "admin";
        String password = "Grid!@#$";

        // scene_delete(new DecoderLoginInfo(ip, port, userName, password), "1", "2");
        // windows_put(new DecoderLoginInfo(ip, port, userName, password), "1",
        // "16777217",
        // "3", "2");

        // scene(new DecoderLoginInfo(ip, port, userName, password), "1");
        // scene_add(new DecoderLoginInfo(ip, port, userName, password), "1", "999rd");
        // scene_saveData(new DecoderLoginInfo(ip, port, userName, password), "1", "4");
        // scene_sid_activate(new DecoderLoginInfo(ip, port, userName, password), "1",
        // "4");
        // scene_isRunning(new DecoderLoginInfo(ip, port, userName, password), "1");
        // windows_put(
        // new DecoderLoginInfo(ip, port, userName, password),
        // 1,
        // 0,
        // 0,
        // 4,
        // "16777217",
        // "1",
        // "1");
        // windows_put(
        // new DecoderLoginInfo(ip, port, userName, password),
        // 1,
        // 1920,
        // 0,
        // 4,
        // "16777217",
        // "2",
        // "2");

        // windows_VWMWID_delete(new DecoderLoginInfo(ip, port, userName, password), 1,
        // 16777217);

        videowall(new DecoderLoginInfo(ip, port, userName, password));
        // get_videowall_scale(new DecoderLoginInfo(ip, port, userName, password), "6");
    }

    public static void windows_update(
            DecoderLoginInfo decoderLoginInfo,
            String videoWallId,
            int x,
            int y,
            int windowMode,
            String windowId,
            String subWindowId,
            String streamingId) {
        String url = IsapiUrl.windows;

        url = url.replace("<videoWallID>", videoWallId);
        url = url + "/" + windowId;

        WallWindowXmlConvert wallWindowXmlConvert =
                new WallWindowXmlConvert(x, y, 1920, 1920, windowMode, subWindowId, streamingId);

        wallWindowXmlConvert.setWindowId(windowId);

        String input = wallWindowXmlConvert.getPutXMl();

        String response =
                HttpClientAuthScopeUtils.doPut(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        input);

        log.info("response --> {}", response);
    }

    /**
     * 删除场景
     *
     * @param decoderLoginInfo
     * @param videoWallID
     * @param SID
     */
    public static void scene_delete(
            DecoderLoginInfo decoderLoginInfo, String videoWallID, String SID) {

        String url = IsapiUrl.scene_sid;

        url = url.replace("<videoWallID>", videoWallID);
        url = url.replace("<SID>", SID);

        String response =
                HttpClientAuthScopeUtils.doDelete(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);
    }

    public static HikDecoderResponseStatus scene_update(
            DecoderLoginInfo decoderLoginInfo,
            String videoWallID,
            String sceneId,
            String sceneName) {

        String url = IsapiUrl.scene_sid;

        url = url.replace("<videoWallID>", videoWallID);
        url = url.replace("<SID>", sceneId);

        WallScene wallScene = new WallScene();
        wallScene.setId(sceneId);
        wallScene.setName(sceneName);

        String input = wallScene.getPutXml();

        log.info("input --> {}", input);

        String response =
                HttpClientAuthScopeUtils.doPut(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        input);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("ResponseStatus", HikDecoderResponseStatus.class);

        xs.allowTypes(
                new Class[] {
                    String.class, Integer.class, List.class, HikDecoderResponseStatus.class
                });

        HikDecoderResponseStatus result = (HikDecoderResponseStatus) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * 添加场景
     *
     * @param decoderLoginInfo
     * @param videoWallID
     * @param sceneName
     */
    public static HikDecoderResponseStatus scene_add(
            DecoderLoginInfo decoderLoginInfo, String videoWallID, String sceneName) {

        String url = IsapiUrl.scene;

        url = url.replace("<videoWallID>", videoWallID);

        WallScene wallScene = new WallScene();
        wallScene.setName(sceneName);

        String input = wallScene.getXMl();

        log.info("input --> {}", input);

        String response =
                HttpClientAuthScopeUtils.doPost(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        input);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("ResponseStatus", HikDecoderResponseStatus.class);

        xs.allowTypes(
                new Class[] {
                    String.class, Integer.class, List.class, HikDecoderResponseStatus.class
                });

        HikDecoderResponseStatus result = (HikDecoderResponseStatus) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * 创建场景之后，需要保存data，场景才可以正常使用
     *
     * @param decoderLoginInfo
     * @param videoWallID
     * @param SID
     * @return
     */
    public static HikDecoderResponseStatus scene_saveData(
            DecoderLoginInfo decoderLoginInfo, String videoWallID, String SID) {

        String url = IsapiUrl.scene_sid_saveData;

        url = url.replace("<videoWallID>", videoWallID);
        url = url.replace("<SID>", SID);

        String response =
                HttpClientAuthScopeUtils.doPut(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        "");

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("ResponseStatus", HikDecoderResponseStatus.class);

        xs.allowTypes(
                new Class[] {
                    String.class, Integer.class, List.class, HikDecoderResponseStatus.class
                });

        HikDecoderResponseStatus result = (HikDecoderResponseStatus) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    public static RunningScene scene_isRunning(
            DecoderLoginInfo decoderLoginInfo, String videoWallID) {

        String url = IsapiUrl.scene_isRunning;

        url = url.replace("<videoWallID>", videoWallID);

        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("RunningScene", RunningScene.class);

        xs.allowTypes(new Class[] {String.class, Integer.class, List.class, RunningScene.class});

        RunningScene result = (RunningScene) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    public static HikDecoderResponseStatus scene_sid_activate(
            DecoderLoginInfo decoderLoginInfo, String videoWallID, String SID) {

        String url = IsapiUrl.scene_sid_activate;

        url = url.replace("<videoWallID>", videoWallID);
        url = url.replace("<SID>", SID);

        String response =
                HttpClientAuthScopeUtils.doPut(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        "");

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("ResponseStatus", HikDecoderResponseStatus.class);

        xs.allowTypes(
                new Class[] {
                    String.class, Integer.class, List.class, HikDecoderResponseStatus.class
                });

        HikDecoderResponseStatus result = (HikDecoderResponseStatus) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * 获取场景列表
     *
     * @param decoderLoginInfo
     * @param videoWallID
     * @return
     */
    public static List<WallScene> scene(DecoderLoginInfo decoderLoginInfo, String videoWallID) {
        String url = IsapiUrl.scene;

        url = url.replace("<videoWallID>", videoWallID);

        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("WallSceneList", List.class);
        xs.alias("WallScene", WallScene.class);

        xs.allowTypes(new Class[] {WallScene.class, String.class, Integer.class, List.class});

        List<WallScene> result = (List<WallScene>) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * 获取墙规格
     *
     * @param decoderLoginInfo
     * @param wallID
     * @return
     */
    public static VideoWallScale get_videowall_scale(
            DecoderLoginInfo decoderLoginInfo, String wallID) {

        String url = IsapiUrl.getvideowallscale;

        String input = "{\"searchWallType\": \"single\",\"ID\":" + wallID + "}";

        String result =
                HttpClientAuthScopeUtils.doPost(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        input);

        log.info("result --> {}", result);

        Gson gson = new Gson();
        VideoWallScaleList videoWallScaleList = gson.fromJson(result, VideoWallScaleList.class);

        if (null != videoWallScaleList) {
            List<VideoWallScale> wallList = videoWallScaleList.getWallList();
            if (null != wallList && wallList.size() > 0) {
                return wallList.get(0);
            }
        }

        return null;
    }

    /**
     * 删除配置流
     *
     * @param decoderLoginInfo
     * @param streamId
     */
    public static void streaming_channels_delete(
            DecoderLoginInfo decoderLoginInfo, String streamId) {
        String url = IsapiUrl.streaming_channels + "/" + streamId;

        String response =
                HttpClientAuthScopeUtils.doDelete(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);
    }

    /**
     * 修改电视墙规模
     *
     * @param decoderLoginInfo
     * @param wallID
     * @param rowNum
     * @param colNum
     */
    public static void modify_video_wall_scale(
            DecoderLoginInfo decoderLoginInfo, String wallID, int rowNum, int colNum) {
        String url = IsapiUrl.modifyvideowallscale;

        WallScaleJsonConvert wallScaleJsonConvert =
                new WallScaleJsonConvert(wallID, rowNum, colNum);

        String response =
                HttpClientAuthScopeUtils.doPost(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        wallScaleJsonConvert.getJson());

        log.info("response --> {}", response);
    }

    /**
     * 获取所有窗口参数配置
     *
     * @param decoderLoginInfo
     * @param videoWallID
     * @return
     */
    public static List<WallWindow> windows_VWMWID(
            DecoderLoginInfo decoderLoginInfo, String videoWallID) {
        String url = IsapiUrl.windows_VWMWID;

        url = url.replace("<videoWallID>", videoWallID);
        url = url.replace("/<VWMWID>", "");

        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("WallWindowList", List.class);
        xs.alias("WallWindow", WallWindow.class);
        xs.alias("Rect", RectXml.class);
        xs.alias("Coordinate", CoordinateXml.class);
        xs.alias("SubWindowList", List.class);
        xs.alias("SubWindow", SubWindow.class);
        xs.alias("SubWindowParam", SubWindowParam.class);

        xs.allowTypes(
                new Class[] {
                    WallWindow.class,
                    RectXml.class,
                    CoordinateXml.class,
                    SubWindow.class,
                    SubWindowParam.class,
                    String.class,
                    Integer.class,
                    List.class
                });

        List<WallWindow> result = (List<WallWindow>) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * 获取单个窗口参数配置
     *
     * @param decoderLoginInfo
     * @param videoWallID
     * @param VWMWID
     * @return
     */
    public static WallWindow windows_VWMWID_get(
            DecoderLoginInfo decoderLoginInfo, String videoWallID, String VWMWID) {
        String url = IsapiUrl.windows_VWMWID;

        url = url.replace("<videoWallID>", videoWallID);
        url = url.replace("<VWMWID>", VWMWID);

        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("WallWindow", WallWindow.class);
        xs.alias("Rect", RectXml.class);
        xs.alias("Coordinate", CoordinateXml.class);
        xs.alias("SubWindowList", List.class);
        xs.alias("SubWindow", SubWindow.class);
        xs.alias("SubWindowParam", SubWindowParam.class);

        xs.allowTypes(
                new Class[] {
                    WallWindow.class,
                    RectXml.class,
                    CoordinateXml.class,
                    SubWindow.class,
                    SubWindowParam.class,
                    String.class,
                    Integer.class,
                    List.class
                });

        WallWindow result = (WallWindow) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * 清理墙上窗口
     *
     * @param decoderLoginInfo
     * @param videoWallID
     * @param VWMWID
     */
    public static void windows_VWMWID_delete(
            DecoderLoginInfo decoderLoginInfo, int videoWallID, int VWMWID) {
        String url = IsapiUrl.windows_VWMWID;

        url = url.replace("<videoWallID>", videoWallID + "");
        url = url.replace("<VWMWID>", VWMWID + "");

        String response =
                HttpClientAuthScopeUtils.doDelete(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);
    }

    /**
     * 清理墙上所有窗口的视频流
     *
     * @param decoderLoginInfo
     * @param videoWallID
     */
    public static void windows_delete(DecoderLoginInfo decoderLoginInfo, int videoWallID) {
        String url = IsapiUrl.windows;

        url = url.replace("<videoWallID>", videoWallID + "");

        String response =
                HttpClientAuthScopeUtils.doDelete(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);
    }

    /**
     * 添加网络信号源
     *
     * @param decoderLoginInfo
     * @param streamInputChannelXmlConvert
     */
    public static void streaming_channels_add(
            DecoderLoginInfo decoderLoginInfo,
            StreamInputChannelXmlConvert streamInputChannelXmlConvert) {
        String url = IsapiUrl.streaming_channels;

        // StreamInputChannelXmlConvert streamInputChannelXmlConvert =
        // new StreamInputChannelXmlConvert(
        // "",
        // "摄像头",
        // camera.getIp(),
        // "8000",
        // camera.getUserName(),
        // camera.getPassword());

        String response =
                HttpClientAuthScopeUtils.doPost(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        streamInputChannelXmlConvert.getXml());

        log.info("response --> {}", response);
    }

    /**
     * 删除墙上所有输出口配置
     *
     * @param decoderLoginInfo
     * @param wallID
     */
    public static void outputs_delete(DecoderLoginInfo decoderLoginInfo, int wallID) {
        String url = IsapiUrl.outputs;

        url = url.replace("<videoWallID>", wallID + "");

        String response =
                HttpClientAuthScopeUtils.doDelete(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);
    }

    /**
     * 删除墙开窗对应的输出口配置
     *
     * @param decoderLoginInfo
     * @param wallID
     * @param outputID
     */
    public static void outputs_output_delete(
            DecoderLoginInfo decoderLoginInfo, int wallID, int outputID) {
        String url = IsapiUrl.outputs;

        url = url.replace("<videoWallID>", wallID + "");
        url = url.replace("<outputID>", outputID + "");

        String response =
                HttpClientAuthScopeUtils.doDelete(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);
    }

    /**
     * 配置墙开窗对应的视频流
     *
     * @param decoderLoginInfo
     */
    public static HikDecoderResponseStatus windows_add(
            DecoderLoginInfo decoderLoginInfo,
            int videoWallId,
            int x,
            int y,
            int windowMode,
            String subWindowId,
            String streamingId) {
        String url = IsapiUrl.windows;

        url = url.replace("<videoWallID>", videoWallId + "");

        WallWindowXmlConvert wallWindowXmlConvert =
                new WallWindowXmlConvert(x, y, 1920, 1920, windowMode, subWindowId, streamingId);

        String input = wallWindowXmlConvert.getXMl();

        String response =
                HttpClientAuthScopeUtils.doPost(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        input);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("ResponseStatus", HikDecoderResponseStatus.class);

        xs.allowTypes(
                new Class[] {
                    String.class, Integer.class, List.class, HikDecoderResponseStatus.class
                });

        HikDecoderResponseStatus result = (HikDecoderResponseStatus) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * @param decoderLoginInfo
     * @param videoWallId 墙id
     * @param windowId 窗口id
     * @param subWindowId 窗口子窗口id
     * @param streamingId 视频流id
     */
    public static void windows_put(
            DecoderLoginInfo decoderLoginInfo,
            String videoWallId,
            String windowId,
            String subWindowId,
            String streamingId) {
        String url = IsapiUrl.windows_VWMWID_VWSWID;

        url = url.replace("<videoWallID>", videoWallId);
        url = url.replace("<VWMWID>", windowId);
        url = url.replace("<VWSWID>", subWindowId);

        log.info("url --> {}", url);

        SubWindowParamPutXmlConvert subWindowParamPutXmlConvert =
                new SubWindowParamPutXmlConvert(subWindowId, streamingId);

        String input = subWindowParamPutXmlConvert.getXml();

        log.info("input --> {}", input);

        String response =
                HttpClientAuthScopeUtils.doPut(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        input);

        log.info("response --> {}", response);
    }

    public static void outputs_capabilities(DecoderLoginInfo decoderLoginInfo, String videoWallID) {
        String url = IsapiUrl.outputs_capabilities;
        url = url.replace("<videoWallID>", videoWallID);

        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);
    }

    /** 获取全部输出口绑定屏幕配置 */
    public static List<WallOutputXml> outputs(
            DecoderLoginInfo decoderLoginInfo, String videoWallID) {

        String url = IsapiUrl.outputs;
        url = url.replace("<videoWallID>", videoWallID);

        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("WallOutputList", List.class);
        xs.alias("WallOutput", WallOutputXml.class);
        xs.alias("Coordinate", CoordinateXml.class);
        xs.alias("Rect", RectXml.class);

        xs.allowTypes(
                new Class[] {
                    String.class,
                    Integer.class,
                    List.class,
                    WallOutputXml.class,
                    CoordinateXml.class,
                    RectXml.class
                });

        List<WallOutputXml> result = (List<WallOutputXml>) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * 输出口绑定屏幕参数
     *
     * @param decoderLoginInfo
     * @param videoWallID
     * @param outputId
     * @param x
     * @param y
     */
    public static void config_outputs(
            DecoderLoginInfo decoderLoginInfo, String videoWallID, String outputId, int x, int y) {

        WallOutputListXmlConvert wallOutputListXmlGenerate =
                // new WallOutputListXmlConvert(16842755, 0, 0, 1920, 1920);
                new WallOutputListXmlConvert(outputId, x, y, 1920, 1920);

        String input = wallOutputListXmlGenerate.getXMl();

        log.info("input --> {}", input);

        String url = IsapiUrl.outputs;
        url = url.replace("<videoWallID>", videoWallID);

        log.info("url --> {}", url);

        String response =
                HttpClientAuthScopeUtils.doPut(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url,
                        input);
        log.info("response --> {}", response);
    }

    /** "获取网络信号源" */
    public static List<StreamInputChannel> streaming_channels(DecoderLoginInfo decoderLoginInfo) {
        String url = IsapiUrl.streaming_channels;

        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("StreamInputChannelList", List.class);
        xs.alias("StreamInputChannel", StreamInputChannel.class);
        xs.alias("StreamInput", StreamInput.class);
        xs.alias("StreamInputRealtime", StreamInputRealtime.class);
        xs.alias("StreamRealtimeUnitList", List.class);
        xs.alias("StreamRealtimeUnit", StreamRealtimeUnit.class);
        xs.alias("StreamByDomain", StreamByDomain.class);
        xs.alias("EncodeDevInfo", EncodeDevInfo.class);
        xs.alias("MediaGatewayInfo", MediaGatewayInfo.class);

        xs.allowTypes(
                new Class[] {
                    String.class,
                    Integer.class,
                    List.class,
                    StreamInputChannel.class,
                    StreamInput.class,
                    StreamInputRealtime.class,
                    StreamRealtimeUnit.class,
                    StreamByDomain.class,
                    EncodeDevInfo.class,
                    MediaGatewayInfo.class
                });

        List<StreamInputChannel> result = (List<StreamInputChannel>) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * 获取全部视频输出基本参数
     *
     * @param decoderLoginInfo
     * @return
     */
    public static List<VideoOutputChannel> outputs_channels(DecoderLoginInfo decoderLoginInfo) {

        String url = IsapiUrl.outputs_channels;
        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("VideoOutputChannelList", List.class);
        xs.alias("VideoOutputChannel", VideoOutputChannel.class);
        xs.alias("OutputResolution", OutputResolution.class);
        xs.alias("PortInBoard", PortInBoard.class);

        xs.allowTypes(
                new Class[] {
                    String.class,
                    Integer.class,
                    List.class,
                    VideoOutputChannel.class,
                    OutputResolution.class,
                    PortInBoard.class
                });

        List<VideoOutputChannel> result = (List<VideoOutputChannel>) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * 获取全部视频墙参数配置
     *
     * @return
     */
    public static List<VideoWall> videowall(DecoderLoginInfo decoderLoginInfo) {

        String url = IsapiUrl.videowall;
        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());
        xs.alias("VideoWallList", List.class);
        xs.alias("VideoWall", VideoWall.class);

        xs.allowTypes(new Class[] {String.class, Integer.class, List.class, VideoWall.class});

        List<VideoWall> result = (List<VideoWall>) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    /**
     * 获取全部信号源配置
     *
     * @param decoderLoginInfo
     */
    public static void inputs_channels(DecoderLoginInfo decoderLoginInfo) {
        String url = IsapiUrl.inputs_channels;

        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        log.info("response --> {}", response);
    }

    /**
     * 获取电视墙能力
     *
     * @return
     */
    public static VideoWallCap videowall_capabilities(
            String ip, short port, String userName, String password) {

        String url = IsapiUrl.capabilities;
        String response = HttpClientAuthScopeUtils.doGet(ip, port, userName, password, url);

        log.info("response --> {}", response);

        XStreamEx xs = new XStreamEx(new DomDriver());

        xs.alias("VideoWallCap", VideoWallCap.class);
        xs.allowTypes(new Class[] {String.class, Integer.class, VideoWallCap.class});

        VideoWallCap result = (VideoWallCap) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }
}
