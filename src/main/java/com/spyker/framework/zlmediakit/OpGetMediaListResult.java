package com.spyker.framework.zlmediakit;

import lombok.Data;

import java.util.List;

@Data
public class OpGetMediaListResult {

    /**
     * {
     * "code" : 0,
     * "data" : [
     * {
     * "app" : "live",  # 应用名
     * "readerCount" : 0, # 本协议观看人数
     * "totalReaderCount" : 0, # 观看总人数，包括hls/rtsp/rtmp/http-flv/ws-flv
     * "schema" : "rtsp", # 协议
     * "stream" : "obs", # 流id
     * "originSock": {  # 客户端和服务器网络信息，可能为null类型
     * "identifier": "140241931428384",
     * "local_ip": "127.0.0.1",
     * "local_port": 1935,
     * "peer_ip": "127.0.0.1",
     * "peer_port": 50097
     * },
     * "originType": 1, # 产生源类型，包括 unknown =
     * 0,rtmp_push=1,rtsp_push=2,rtp_push=3,pull=4,ffmpeg_pull=5,mp4_vod=6,device_chn=7
     * "originTypeStr": "MediaOriginType::rtmp_push",
     * "originUrl": "rtmp://127.0.0.1:1935/live/hks2", #产生源的url
     * "createStamp": 1602205811, #GMT unix系统时间戳，单位秒
     * "aliveSecond": 100, #存活时间，单位秒
     * "bytesSpeed": 12345, #数据产生速度，单位byte/s
     * "tracks" : [    # 音视频轨道
     * {
     * "channels" : 1, # 音频通道数
     * "codec_id" : 2, # H264 = 0, H265 = 1, AAC = 2, G711A = 3, G711U = 4
     * "codec_id_name" : "CodecAAC", # 编码类型名称
     * "codec_type" : 1, # Video = 0, Audio = 1
     * "ready" : true, # 轨道是否准备就绪
     * "frames" : 1119, #累计接收帧数
     * "sample_bit" : 16, # 音频采样位数
     * "sample_rate" : 8000 # 音频采样率
     * },
     * {
     * "codec_id" : 0, # H264 = 0, H265 = 1, AAC = 2, G711A = 3, G711U = 4
     * "codec_id_name" : "CodecH264", # 编码类型名称
     * "codec_type" : 0, # Video = 0, Audio = 1
     * "fps" : 59,  # 视频fps
     * "frames" : 1119, #累计接收帧数，不包含sei/aud/sps/pps等不能解码的帧
     * "gop_interval_ms" : 1993, #gop间隔时间，单位毫秒
     * "gop_size" : 60, #gop大小，单位帧数
     * "key_frames" : 21, #累计接收关键帧数
     * "height" : 720, # 视频高
     * "ready" : true,  # 轨道是否准备就绪
     * "width" : 1280 # 视频宽
     * }
     * ],
     * "vhost" : "__defaultVhost__" # 虚拟主机名
     * }
     * ]
     * }
     */

    int code;

    MediaInfo data;

    @Data
    public class MediaInfo {

        String app;
        int readerCount;
        int totalReaderCount;
        String schema;
        String stream;

        OriginSock originSock;

        int originType;
        String originTypeStr;
        String originUrl;
        String createStamp;
        String aliveSecond;
        String bytesSpeed;

        List<TrackInfo> tracks;

        String vhost;

    }

    @Data
    class OriginSock {

        /**
         * identifier": "140241931428384",
         * "local_ip": "127.0.0.1",
         * "local_port": 1935,
         * "peer_ip": "127.0.0.1",
         * "peer_port": 50097
         */

        String identifier;
        String local_ip;
        int local_port;
        String peer_ip;
        int peer_port;
    }

    public class TrackInfo {

        /**
         * "channels" : 1, # 音频通道数
         * "codec_id" : 2, # H264 = 0, H265 = 1, AAC = 2, G711A = 3, G711U = 4
         * "codec_id_name" : "CodecAAC", # 编码类型名称
         * "codec_type" : 1, # Video = 0, Audio = 1
         * "ready" : true, # 轨道是否准备就绪
         * "frames" : 1119, #累计接收帧数
         * "sample_bit" : 16, # 音频采样位数
         * "sample_rate" : 8000 # 音频采样率
         */

        int channels;
        int codec_id;
        String codec_id_name;
        int codec_type;
        String ready;
        int frames;
        int sample_bit;
        int sample_rate;

    }
}