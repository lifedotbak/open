package com.spyker.framework.isapi;

/** 所有url请求遵循rest风格，POST提交新增，PUT修改，DELETE删除，GET获取 */
public interface IsapiUrl {

    /**
     * int, 输出口编号, desc:输出口编号列表通过协议：GET /ISAPI/DisplayDev/Video/outputs/channels 中的id字段获取;
     * 输出口编号组成规则（四个字节，按十进制返 回）： 1个高字节表示设备号，1个次高字节表示子板号，2个低字节表示输出口在子板上的顺序号。 设备号：默认为1；
     * 子板号：子板的编号，从1开始；输出口在子板上的顺序号：从1开始。如十进制16842753对应十六进制0x1010001：表示设备 号为1的1号子板上的第1个输出口--> 16842753
     */
    int output_start = 16842753;

    /** get-->获取全部场景 */
    String scene = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/scene";

    /** 单个场景操作 DELETE--->删除 */
    String scene_sid = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/scene/<SID>";

    /** put方法调用 */
    String scene_sid_saveData = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/scene/<SID>/saveData";

    String scene_sid_activate = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/scene/<SID>/activate";

    /** 获取当前激活的场景 GET访问 */
    String scene_isRunning = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/scene/isRunning";

    /**
     * 获取电视墙规模,POST方法, *req, string, 查询墙类型, const:, range:[,], enum:[all#所有墙, single#单个墙], format:,
     * mediaType:, pattern:, unit:, RAP:, desc:
     */
    String getvideowallscale = "/ISAPI/DisplayDev/VideoWall/GetVideoWallScale?format=json";

    /** 获取电视墙能力 */
    String capabilities = "/ISAPI/DisplayDev/VideoWall/capabilities";

    /** 获取全部视频墙参数配置 */
    String videowall = "/ISAPI/DisplayDev/VideoWall";

    /** 获取全部视频输出基本参数 */
    String outputs_channels = "/ISAPI/DisplayDev/Video/outputs/channels";

    String outputs_channels_channelId = "/ISAPI/DisplayDev/Video/outputs/channels/<channelID>";

    /** 获取全部信号源配置 */
    String inputs_channels = "/ISAPI/DisplayDev/Video/inputs/channels";

    /** 网络信号源 POST提交新增，PUT修改，DELETE删除，GET获取 */
    String streaming_channels = "/ISAPI/DisplayDev/Video/streaming/channels";

    /** 配置全部输出口和屏幕解绑 */
    String outputs_reset = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/outputs/reset";

    /** 添加输出口绑定屏幕参数 POST-->添加 ,PUT修改, DELETE删除 */
    String outputs = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/outputs";

    /** 墙上开窗分配视频流 POST提交,DELETE删除墙对应的视频流,GET方法获取墙上所有窗口信息 */
    String windows = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/windows";

    /** 配置单个窗口参数配置 POST提交,DELETE删除墙对应的视频流,GET方法获取墙上所有窗口信息 */
    String windows_VWMWID = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/windows/<VWMWID>";

    /** 配置子窗口视频流 */
    String windows_VWMWID_VWSWID =
            "/ISAPI/DisplayDev/VideoWall/<videoWallID>/windows/<VWMWID>/sub/<VWSWID>";

    String outputs_capabilities = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/outputs/capabilities";

    /**
     * 修改墙的规模
     *
     * <p>{ "ID": 1, "rowNum": 2, "colNum": 2 }
     */
    String modifyvideowallscale = "/ISAPI/DisplayDev/VideoWall/ModifyVideoWallScale?format=json";

    //    /** 清理墙所有输出口参数 DELETE方法 */
    //    String outputs_reset_delete = "/ISAPI/DisplayDev/VideoWall/<videoWallID>/outputs";
}
