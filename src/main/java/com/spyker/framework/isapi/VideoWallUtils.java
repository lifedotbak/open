package com.spyker.framework.isapi;

import com.spyker.framework.isapi.convert.StreamInputChannelXmlConvert;
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
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class VideoWallUtils {

    private static final String XML_HEARD = "<?xml version='1.0' encoding='UTF-8'?>";

    public static void main(String[] args) throws Exception {

        String ip = "192.168.15.195";
        short port = 80;
        String userName = "admin";
        String password = "Grid!@#$";

        modify_video_wall_scale(new DecoderLoginInfo(ip, port, userName, password), "3", 4, 4);

        //                config_stream(ip, port, userName, password);

        //                List<StreamInputChannel> videoInputChannelList =
        //                        streaming_channels(ip, port, userName, password);
        //
        //        log.info("videoInputChannelList --> {}", videoInputChannelList);

        //        config_wall_stream(ip, port, userName, password);

        //     List<VideoWall> videoWallList = videowall(ip, port, userName, password);
        //
        //        List<VideoOutputChannel> videoOutputChannelList =
        //                outputs_channels(ip, port, userName, password);
        //
        //        step_4_put(ip, port, userName, password, videoOutputChannelList);
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
        String url = IsapiUrl.modify_video_wall_scale_post;

        WallScaleJsonConvert wallScaleJsonConvert =
                new WallScaleJsonConvert(wallID, rowNum, colNum);

        HttpClientAuthScopeUtils.doPost(
                decoderLoginInfo.getIp(),
                decoderLoginInfo.getPort(),
                decoderLoginInfo.getUserName(),
                decoderLoginInfo.getPassword(),
                url,
                wallScaleJsonConvert.getJson());
    }

    public static void delete_wall_stream(DecoderLoginInfo decoderLoginInfo) {
        String url = IsapiUrl.config_wall_windows;

        url = url.replace("<videoWallID>", "1");

        HttpClientAuthScopeUtils.doDelete(
                decoderLoginInfo.getIp(),
                decoderLoginInfo.getPort(),
                decoderLoginInfo.getUserName(),
                decoderLoginInfo.getPassword(),
                url);
    }

    public static void add_streaming_channels(DecoderLoginInfo decoderLoginInfo) {
        String url = IsapiUrl.streaming_channels;

        StreamInputChannelXmlConvert streamInputChannelXmlConvert =
                new StreamInputChannelXmlConvert(
                        "222", "222", "192.168.15.220", "8000", "admin", "grid123456");

        HttpClientAuthScopeUtils.doPost(
                decoderLoginInfo.getIp(),
                decoderLoginInfo.getPort(),
                decoderLoginInfo.getUserName(),
                decoderLoginInfo.getPassword(),
                url,
                streamInputChannelXmlConvert.getXml());
    }

    public static void delete_wall_output_config(DecoderLoginInfo decoderLoginInfo, int wallID) {
        String url = IsapiUrl.config_wall_outputs;

        url = url.replace("<videoWallID>", wallID + "");

        HttpClientAuthScopeUtils.doDelete(
                decoderLoginInfo.getIp(),
                decoderLoginInfo.getPort(),
                decoderLoginInfo.getUserName(),
                decoderLoginInfo.getPassword(),
                url);
    }

    /**
     * 配置墙开窗对应的视频流
     *
     * @param decoderLoginInfo
     */
    public static void config_wall_windows(DecoderLoginInfo decoderLoginInfo, int videoWallID) {
        String url = IsapiUrl.config_wall_windows;

        url = url.replace("<videoWallID>", videoWallID + "");

        WallWindowXmlConvert wallWindowXmlConvert =
                new WallWindowXmlConvert(0, 0, 1920, 1920, 4, "1", "1");

        String input = wallWindowXmlConvert.getXMl();

        HttpClientAuthScopeUtils.doPost(
                decoderLoginInfo.getIp(),
                decoderLoginInfo.getPort(),
                decoderLoginInfo.getUserName(),
                decoderLoginInfo.getPassword(),
                url,
                input);
    }

    public static void config_wall_output(
            DecoderLoginInfo decoderLoginInfo, String videoWallID, String outputId) {

        WallOutputListXmlConvert wallOutputListXmlGenerate =
                //                new WallOutputListXmlConvert(16842755, 0, 0, 1920, 1920);
                new WallOutputListXmlConvert(outputId, 0, 0, 1920, 1920);

        String input = wallOutputListXmlGenerate.getXMl();

        log.info("input --> {}", input);

        String url = IsapiUrl.config_wall_outputs;
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

        XStream xs = new XStream(new DomDriver());
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

    public static void config_stream(DecoderLoginInfo decoderLoginInfo) {
        String url = "/ISAPI/DisplayDev/VideoWall/1/windows/16842755/sub/1";

        HttpClientAuthScopeUtils.doPut(
                decoderLoginInfo.getIp(),
                decoderLoginInfo.getPort(),
                decoderLoginInfo.getUserName(),
                decoderLoginInfo.getPassword(),
                url,
                "");
    }

    public static List<VideoOutputChannel> outputs_channels(DecoderLoginInfo decoderLoginInfo) {

        String url = IsapiUrl.outputs_channels;
        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);

        XStream xs = new XStream(new DomDriver());
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

        XStream xs = new XStream(new DomDriver());
        xs.alias("VideoWallList", List.class);
        xs.alias("VideoWall", VideoWall.class);

        xs.allowTypes(new Class[] {String.class, Integer.class, List.class, VideoWall.class});

        List<VideoWall> result = (List<VideoWall>) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    public static void inputs_channels(DecoderLoginInfo decoderLoginInfo) {
        String url = IsapiUrl.inputs_channels;
        String response =
                HttpClientAuthScopeUtils.doGet(
                        decoderLoginInfo.getIp(),
                        decoderLoginInfo.getPort(),
                        decoderLoginInfo.getUserName(),
                        decoderLoginInfo.getPassword(),
                        url);
    }

    /**
     * 获取电视墙能力
     *
     * @return
     */
    public static VideoWallCap videowall_capabilities(
            String ip, short port, String userName, String password) {

        String url = IsapiUrl.videowall_capabilities;
        String response = HttpClientAuthScopeUtils.doGet(ip, port, userName, password, url);

        XStreamEx xs = new XStreamEx(new DomDriver());

        xs.alias("VideoWallCap", VideoWallCap.class);

        xs.allowTypes(new Class[] {String.class, Integer.class, VideoWallCap.class});

        VideoWallCap result = (VideoWallCap) xs.fromXML(response);

        log.info("result --> {}", result);

        return result;
    }

    public static void step_4_put(
            String ip,
            short port,
            String userName,
            String password,
            List<VideoOutputChannel> videoOutputChannelList) {

        if (null != videoOutputChannelList && videoOutputChannelList.size() > 0) {

            String url = IsapiUrl.step_4_put;

            int i = 0;

            for (VideoOutputChannel videoOutputChannel : videoOutputChannelList) {

                //                i = i + 1;
                //
                //                if (i == 3) {

                videoOutputChannel.setVersion("2.0");
                videoOutputChannel.setXmlns("http://www.isapi.org/ver20/XMLSchema");

                videoOutputChannel.setName("zhilian_X_" + videoOutputChannel.getId());

                url = url.replace("<channelID>", videoOutputChannel.getId());

                XStream xs = new XStream(new DomDriver());

                xs.autodetectAnnotations(true); // 自动检测注解
                xs.processAnnotations(VideoOutputChannel.class);

                xs.alias("VideoOutputChannel", VideoOutputChannel.class);

                xs.aliasSystemAttribute("version", "version");
                xs.aliasSystemAttribute("xmlns", "xmlns");

                xs.alias("OutputResolution", OutputResolution.class);
                xs.alias("PortInBoard", PortInBoard.class);

                String input = xs.toXML(videoOutputChannel);

                input = XML_HEARD + input;

                log.info("input --> {}", input);

                String response =
                        HttpClientAuthScopeUtils.doPut(ip, port, userName, password, url, input);
                log.info("response --> {}", response);
                //                }
            }
        }
    }
}