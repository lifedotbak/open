package com.spyker.framework.third.hikvision.jna;

import com.sun.jna.*;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;

//SDK接口说明,HCNetSDK.dll
public interface HCNetSDK extends Library {

    int NAME_LEN = 32; // 用户名长度
    int PASSWD_LEN = 16; // 密码长度
    int SERIALNO_LEN = 48; // 序列号长度

    int NET_DVR_DEV_ADDRESS_MAX_LEN = 129;
    int NET_DVR_LOGIN_USERNAME_MAX_LEN = 64;
    int NET_DVR_LOGIN_PASSWD_MAX_LEN = 64;
    int NET_SDK_MAX_FILE_PATH = 256;// 路径长度
    int TILT_UP = 21; /* 云台以SS的速度上仰 */
    int TILT_DOWN = 22; /* 云台以SS的速度下俯 */
    int PAN_LEFT = 23; /* 云台以SS的速度左转 */
    int PAN_RIGHT = 24; /* 云台以SS的速度右转 */
    int UP_LEFT = 25; /* 云台以SS的速度上仰和左转 */
    int UP_RIGHT = 26; /* 云台以SS的速度上仰和右转 */
    int DOWN_LEFT = 27; /* 云台以SS的速度下俯和左转 */
    int DOWN_RIGHT = 28; /* 云台以SS的速度下俯和右转 */
    int PAN_AUTO = 29; /* 云台以SS的速度左右自动扫描 */
    int NET_DVR_PLAYGETPOS = 13;// 获取文件回放的进度
    /********************
     * 预览回调函数
     *********************/
    int NET_DVR_SYSHEAD = 1;// 系统头数据
    int NET_DVR_STREAMDATA = 2;// 视频流数据（包括复合流和音视频分开的视频流数据）

    int MAX_ANALOG_CHANNUM = 32; // 最大32个模拟通道

    int MAX_IP_CHANNEL = 32; // 允许加入的最多IP通道数

    // 最大支持的通道数 最大模拟加上最大IP支持
    int MAX_CHANNUM_V30 = (MAX_ANALOG_CHANNUM + MAX_IP_CHANNEL);// 64

    int MAX_IP_DEVICE_V40 = 64;
    int STREAM_ID_LEN = 32;
    int MAX_DOMAIN_NAME = 64; /* 最大域名长度 */

    int NET_DVR_GET_IPPARACFG_V40 = 1062; // 获取IP接入配置信息

    int NET_DVR_PLAYSTART = 1;// 开始播放
    int CARDNUM_LEN_OUT = 32; // 外部结构体卡号长度
    int GUID_LEN = 16; // GUID长度

    /**
     * 转到预置点
     */
    int GOTO_PRESET = 39;

    int NET_DVR_SET_TIMECFG = 119; // 设置时间参数（校时）

    /**
     * @param lUserID
     * @param dwCommand
     * @param lChannel       通道号，不同的命令对应不同的取值，如果该参数无效则置为0xFFFFFFFF即可，详见“Remarks”说明
     * @param lpInBuffer
     * @param dwInBufferSize
     * @return
     */
    boolean NET_DVR_SetDVRConfig(int lUserID, int dwCommand, int lChannel, Pointer lpInBuffer, int dwInBufferSize);

    boolean NET_DVR_CaptureJPEGPicture(int lUserID, int lChannel, NET_DVR_JPEGPARA lpJpegPara, byte[] sPicFileName);

    int NET_DVR_PlayBackByTime_V40(int lUserID, NET_DVR_VOD_PARA pVodPara);

    boolean NET_DVR_StopRealPlay(int lRealHandle);

    boolean NET_DVR_SaveRealData(int lRealHandle, String sFileName);

    int NET_DVR_GetFileByTime_V40(int lUserID, String sSavedFileName, NET_DVR_PLAYCOND pDownloadCond);

    boolean NET_DVR_StopPlayBack(int lPlayHandle);

    boolean NET_DVR_PlayBackControl(int lPlayHandle, int dwControlCode, int dwInValue, IntByReference LPOutValue);

    // 启用日志文件写入接口
    boolean NET_DVR_SetLogToFile(int bLogEnable, String strLogDir, boolean bAutoDel);

    // NET_DVR_SetDVRMessage的扩展
    boolean NET_DVR_SetExceptionCallBack_V30(int nMessage, int hWnd, FExceptionCallBack fExceptionCallBack,
            Pointer pUser);

    boolean NET_DVR_Init();

    boolean NET_DVR_Cleanup();

    boolean NET_DVR_Logout(int lUserID);

    boolean NET_DVR_PTZControl_Other(int lUserID, int lChannel, int dwPTZCommand, int dwStop);

    boolean NET_DVR_PTZPreset_Other(int lUserID, int lChannel, int dwPTZPresetCmd, int dwPresetIndex);

    boolean NET_DVR_SetSDKInitCfg(int enumType, Pointer lpInBuff);

    int NET_DVR_Login_V40(NET_DVR_USER_LOGIN_INFO pLoginInfo, NET_DVR_DEVICEINFO_V40 lpDeviceInfo);

    int NET_DVR_GetLastError();

    boolean NET_DVR_GetDVRConfig(int lUserID, int dwCommand, int lChannel, Pointer lpOutBuffer, int dwOutBufferSize,
            IntByReference lpBytesReturned);

    int NET_DVR_RealPlay_V40(int lUserID, NET_DVR_PREVIEWINFO lpPreviewInfo,
            FRealDataCallBack_V30 fRealDataCallBack_V30, Pointer pUser);

    boolean NET_DVR_FindClose_V30(int lFindHandle);

    int NET_DVR_PlayBackByName(int lUserID, String sPlayBackFileName, HWND hWnd);

    boolean NET_DVR_PlayBackControl_V40(int lPlayHandle, int dwControlCode, Pointer lpInBuffer, int dwInLen,
            Pointer lpOutBuffer, IntByReference lpOutLen);

    int NET_DVR_GetDownloadPos(int lFileHandle);

    boolean NET_DVR_SetPlayDataCallBack_V40(int lPlayHandle, FPlayDataCallBack fPlayDataCallBack, Pointer dwUser);

    int NET_DVR_FindFile_V40(int lUserID, NET_DVR_FILECOND_V40 pFindCond);

    int NET_DVR_FindNextFile_V40(int lFindHandle, NET_DVR_FINDDATA_V40 lpFindData);

    interface FLoginResultCallBack extends Callback {
        int invoke(int lUserID, int dwResult, NET_DVR_DEVICEINFO_V30 lpDeviceinfo, Pointer pUser);
    }

    interface FExceptionCallBack extends Callback {
        void invoke(int dwType, int lUserID, int lHandle, Pointer pUser);
    }

    /***
     * API函数声明,详细说明见API手册
     ***/
    interface FRealDataCallBack_V30 extends Callback {
        void invoke(int lRealHandle, int dwDataType, ByteByReference pBuffer, int dwBufSize, Pointer pUser);
    }

    interface FPlayDataCallBack extends Callback {
        void invoke(int lPlayHandle, int dwDataType, Pointer pBuffer, int dwBufSize, int dwUser);
    }

    class NET_DVR_JPEGPARA extends Structure {
        /*
         * 注意：当图像压缩分辨率为VGA时，支持0=CIF, 1=QCIF, 2=D1抓图， 当分辨率为3=UXGA(1600x1200),
         * 4=SVGA(800x600), 5=HD720p(1280x720),6=VGA,7=XVGA, 8=HD900p 仅支持当前分辨率的抓图
         */
        public short wPicSize;

        /*
         * 0=CIF, 1=QCIF, 2=D1 3=UXGA(1600x1200), 4=SVGA(800x600),
         * 5=HD720p(1280x720),6=VGA
         */
        public short wPicQuality; /* 图片质量系数 0-最好 1-较好 2-一般 */
    }

    class NET_DVR_FINDDATA_V40 extends Structure {
        public byte[] sFileName = new byte[100];// 文件名
        public NET_DVR_TIME struStartTime = new NET_DVR_TIME();// 文件的开始时间
        public NET_DVR_TIME struStopTime = new NET_DVR_TIME();// 文件的结束时间
        public int dwFileSize;// 文件的大小
        public byte[] sCardNum = new byte[32];
        public byte byLocked;// 9000设备支持,1表示此文件已经被锁定,0表示正常的文件
        public byte byFileType; // 文件类型:0－定时录像,1-移动侦测 ，2－报警触发，
        // 3-报警|移动侦测 4-报警&移动侦测 5-命令触发
        // 6-手动录像,7－震动报警，8-环境报警，9-智能报警，10-PIR报警，11-无线报警，12-呼救报警,14-智能交通事件
        public byte byQuickSearch; // 0:普通查询结果，1：快速（日历）查询结果
        public byte byRes;
        public int dwFileIndex; // 文件索引号
        public byte byStreamType;
        public byte[] byRes1 = new byte[127];
    }

    class NET_DVR_STREAM_INFO extends Structure {
        public int dwSize;
        public byte[] byID = new byte[32];
        public int dwChannel;
        public byte[] byRes = new byte[32];
    }

    class NET_DVR_VOD_PARA extends Structure // 回放或者下载信息结构体
    {
        public int dwSize;
        public NET_DVR_STREAM_INFO struIDInfo;
        public NET_DVR_TIME struBeginTime;
        public NET_DVR_TIME struEndTime;
        public HWND hWnd;
        public byte byDrawFrame; // 0:不抽帧，1：抽帧
        public byte byVolumeType; // 0-普通录像卷 1-存档卷
        public byte byVolumeNum; // 卷号，目前指存档卷号
        public byte byStreamType; // 码流类型 0-主码流， 1-子码流，2-码流三
        public int dwFileIndex; // 存档卷上的录像文件索引，搜索存档卷录像时返回的值
        public byte byAudioFile; // 音频文件0-否，1-是
        public byte byCourseFile; // 课程文件0-否，1-是
        public byte byDownload; // 是否下载 0-否，1-是
        public byte byOptimalStreamType; // 是否按最优码流类型回放 0-否，1-是（对于双码流设备，某一段时间内的录像文件与指定码流类型不同，则返回实际码流类型的录像）
        public byte[] byRes2 = new byte[20];
    }

    class BYTE_ARRAY extends Structure {
        public byte[] byValue;

        public BYTE_ARRAY(int iLen) {
            byValue = new byte[iLen];
        }
    }

    // 预览V40接口
    class NET_DVR_PREVIEWINFO extends Structure {
        public int lChannel;// 通道号
        public int dwStreamType; // 码流类型，0-主码流，1-子码流，2-码流3，3-码流4, 4-码流5,5-码流6,7-码流7,8-码流8,9-码流9,10-码流10
        public int dwLinkMode;// 0：TCP方式,1：UDP方式,2：多播方式,3 - RTP方式，4-RTP/RTSP,5-RSTP/HTTP ,6- HRUDP（可靠传输）
        // ,7-RTSP/HTTPS
        public int hPlayWnd;// 播放窗口的句柄,为NULL表示不播放图象
        public int bBlocked; // 0-非阻塞取流, 1-阻塞取流, 如果阻塞SDK内部connect失败将会有5s的超时才能够返回,不适合于轮询取流操作.
        public int bPassbackRecord; // 0-不启用录像回传,1启用录像回传
        public byte byPreviewMode;// 预览模式，0-正常预览，1-延迟预览
        public byte[] byStreamID = new byte[32];// 流ID，lChannel为0xffffffff时启用此参数
        public byte byProtoType; // 应用层取流协议，0-私有协议，1-RTSP协议
        public byte byRes1;
        public byte byVideoCodingType; // 码流数据编解码类型 0-通用编码数据 1-热成像探测器产生的原始数据（温度数据的加密信息，通过去加密运算，将原始数据算出真实的温度值）
        public int dwDisplayBufNum; // 播放库播放缓冲区最大缓冲帧数，范围1-50，置0时默认为1
        public byte byNPQMode; // NPQ是直连模式，还是过流媒体 0-直连 1-过流媒体
        public byte[] byRes = new byte[215];
    }

    class NET_DVR_PLAYCOND extends Structure // 回放或者下载信息结构体
    {
        public int dwChannel;// 通道号
        public NET_DVR_TIME struStartTime;
        public NET_DVR_TIME struStopTime;
        public byte byDrawFrame; // 0:不抽帧，1：抽帧
        public byte byStreamType; // 码流类型，0-主码流 1-子码流 2-码流三
        public byte[] byStreamID = new byte[STREAM_ID_LEN];
        public byte[] byRes = new byte[30];// 保留
    }

    class NET_DVR_IPPARACFG_V40 extends Structure {/* IP接入配置结构V40 */
        public int dwSize; /* 结构大小 */
        public int dwGroupNum;// 设备支持的总组数（只读）。
        public int dwAChanNum;// 最大模拟通道个数（只读）
        public int dwDChanNum;// 数字通道个数（只读）
        public int dwStartDChan;// 起始数字通道（只读）
        public byte[] byAnalogChanEnable = new byte[MAX_CHANNUM_V30]; // 模拟通道资源是否启用，从低到高表示1-64通道：0-禁用，1-启用。
        public NET_DVR_IPDEVINFO_V31[] struIPDevInfo = new NET_DVR_IPDEVINFO_V31[MAX_IP_DEVICE_V40];// IP设备信息，下标0对应设备IP
        // ID为1
        public NET_DVR_STREAM_MODE[] struStreamMode = new NET_DVR_STREAM_MODE[MAX_CHANNUM_V30];// 取流模式
        public byte[] byRes2 = new byte[20];// 保留，置为0

    }

    class NET_DVR_STREAM_MODE extends Structure {

        public byte byGetStreamType;// 取流方式：0- 直接从设备取流；1- 从流媒体取流；2- 通过IPServer获得IP地址后取流；
        // 3- 通过IPServer找到设备，再通过流媒体取设备的流； 4- 通过流媒体由URL去取流；5- 通过hiDDNS域名连接设备然后从设备取流
        public byte[] byRes = new byte[3];// 保留，置为0
        public NET_DVR_GET_STREAM_UNION uGetStream = new NET_DVR_GET_STREAM_UNION();// 不同取流方式联合体

        @Override
        public void read() {
            super.read();
            switch (byGetStreamType) {
                case 0:
                    uGetStream.setType(NET_DVR_IPCHANINFO.class);
                    break;
                case 6:
                    uGetStream.setType(NET_DVR_IPCHANINFO_V40.class);
                    break;
                default:
                    break;
            }
        }

    }

    class NET_DVR_IPCHANINFO_V40 extends Structure {

        public byte byEnable;// IP通道在线状态，是一个只读的属性；
        // 0表示HDVR或者NVR设备的数字通道连接对应的IP设备失败，该通道不在线；1表示连接成功，该通道在线
        public byte byRes1;// 保留，置为0
        public short wIPID;// IP设备ID
        public int dwChannel;// IP设备的通道号，例如设备A（HDVR或者NVR设备）的IP通道01，对应的是设备B（DVS）里的通道04，则byChannel=4，如果前端接的是IPC
        // 则byChannel=1。
        public byte byTransProtocol;// 传输协议类型：0- TCP，1- UDP，2- 多播，0xff- auto(自动)
        public byte byTransMode;// 传输码流模式：0- 主码流，1- 子码流
        public byte byFactoryType;// 前端设备厂家类型
        public byte[] byRes = new byte[241];// 保留，置为0

    }

    class NET_DVR_IPCHANINFO extends Structure {/* IP通道匹配参数 */
        public byte byEnable; /* 该通道是否启用 */
        public byte byIPID; /* IP设备ID 取值1- MAX_IP_DEVICE */
        public byte byChannel; /* 通道号 */
        public byte[] byres = new byte[33]; /* 保留 */

    }

    class NET_DVR_GET_STREAM_UNION extends Union {
        public NET_DVR_IPCHANINFO struChanInfo = new NET_DVR_IPCHANINFO(); /* IP通道信息 */
        public NET_DVR_IPCHANINFO_V40 struIPChan = new NET_DVR_IPCHANINFO_V40(); // 直接从设备取流（扩展）
        public byte[] byUnionLen = new byte[492]; // 直接从设备取流（扩展）

    }

    class NET_DVR_IPDEVINFO_V31 extends Structure {
        public byte byEnable;/* 该通道是否启用 */
        public byte byProType;// 协议类型(默认为私有协议)，0- 私有协议，1- 松下协议，2- 索尼，更多协议通过NET_DVR_GetIPCProtoList获取。
        public byte byEnableQuickAdd;// 0-不支持快速添加；1-使用快速添加
        public byte byRes1;// 保留，置为0
        public byte[] sUserName = new byte[HCNetSDK.NAME_LEN];// 用户名
        public byte[] sPassword = new byte[HCNetSDK.PASSWD_LEN];// 密码
        public byte[] byDomain = new byte[HCNetSDK.MAX_DOMAIN_NAME];// 设备域名
        public NET_DVR_IPADDR struIP = new NET_DVR_IPADDR();// IP地址
        public short wDVRPort;// 端口号
        public byte[] szDeviceID = new byte[32];
        public byte[] byRes2 = new byte[2];// 保留，置为0

    }

    class NET_DVR_IPADDR extends Structure {
        public byte[] sIpV4 = new byte[16];
        public byte[] byRes = new byte[128];

        @Override
        public String toString() {
            return "NET_DVR_IPADDR.sIpV4: " + new String(sIpV4) + "\n" + "NET_DVR_IPADDR.byRes: " + new String(byRes) + "\n";
        }

    }

    // NET_DVR_Login_V40()参数
    class NET_DVR_USER_LOGIN_INFO extends Structure {
        public byte[] sDeviceAddress = new byte[NET_DVR_DEV_ADDRESS_MAX_LEN];
        public byte byUseTransport;
        public short wPort;
        public byte[] sUserName = new byte[NET_DVR_LOGIN_USERNAME_MAX_LEN];
        public byte[] sPassword = new byte[NET_DVR_LOGIN_PASSWD_MAX_LEN];
        public FLoginResultCallBack cbLoginResult;
        public Pointer pUser;
        public boolean bUseAsynLogin;
        public byte byProxyType; // 0:不使用代理，1：使用标准代理，2：使用EHome代理
        public byte byUseUTCTime; // 0-不进行转换，默认,1-接口上输入输出全部使用UTC时间,SDK完成UTC时间与设备时区的转换,
        // 2-接口上输入输出全部使用平台本地时间，SDK完成平台本地时间与设备时区的转换
        public byte byLoginMode; // 0-Private 1-ISAPI 2-自适应
        public byte byHttps; // 0-不适用tls，1-使用tls 2-自适应
        public int iProxyID; // 代理服务器序号，添加代理服务器信息时，相对应的服务器数组下表值
        public byte byVerifyMode; // 认证方式，0-不认证，1-双向认证，2-单向认证；认证仅在使用TLS的时候生效;
        public byte[] byRes2 = new byte[119];
    }

    // NET_DVR_Login_V40()参数
    class NET_DVR_DEVICEINFO_V40 extends Structure {
        public NET_DVR_DEVICEINFO_V30 struDeviceV30 = new NET_DVR_DEVICEINFO_V30();
        public byte bySupportLock;
        public byte byRetryLoginTime;
        public byte byPasswordLevel;
        public byte byRes1;
        public int dwSurplusLockTime;
        public byte byCharEncodeType;// 字符编码类型：0- 无字符编码信息(老设备)，1- GB2312(简体中文)，2- GBK，3- BIG5(繁体中文)，4-
        // Shift_JIS(日文)，5- EUC-KR(韩文)，6- UTF-8，7- ISO8859-1，8- ISO8859-2，9-
        // ISO8859-3，…，依次类推，21- ISO8859-15(西欧)
        public byte bySupportDev5; // 支持v50版本的设备参数获取，设备名称和设备类型名称长度扩展为64字节
        public byte bySupport; // 能力集扩展，位与结果：0- 不支持，1- 支持
        public byte byLoginMode; // 登录模式 0-Private登录 1-ISAPI登录
        public int dwOEMCode;
        public int iResidualValidity; // 该用户密码剩余有效天数，单位：天，返回负值，表示密码已经超期使用，例如“-3表示密码已经超期使用3天”
        public byte byResidualValidity; // iResidualValidity字段是否有效，0-无效，1-有效
        public byte bySingleStartDTalkChan; // 独立音轨接入的设备，起始接入通道号，0-为保留字节，无实际含义，音轨通道号不能从0开始
        public byte bySingleDTalkChanNums; // 独立音轨接入的设备的通道总数，0-表示不支持
        public byte byPassWordResetLevel; // 0-无效，1
        // -管理员创建一个非管理员用户为其设置密码，该非管理员用户正确登录设备后要提示“请修改初始登录密码”，未修改的情况下，用户每次登入都会进行提醒；2
        // -当非管理员用户的密码被管理员修改，该非管理员用户再次正确登录设备后，需要提示“请重新设置登录密码”，未修改的情况下，用户每次登入都会进行提醒。
        public byte bySupportStreamEncrypt; // 能力集扩展，位与结果：0- 不支持，1- 支持 bySupportStreamEncrypt & 0x1:表示是否支持RTP/TLS取流
        // bySupportStreamEncrypt & 0x2: 表示是否支持SRTP/UDP取流 bySupportStreamEncrypt &
        // 0x4: 表示是否支持SRTP/MULTICAST取流
        public byte byMarketType;// 0-无效（未知类型）,1-经销型，2-行业型
        public byte[] byRes2 = new byte[238];
    }

    class NET_DVR_FILECOND_V40 extends Structure {
        public int lChannel;
        public int dwFileType;
        public int dwIsLocked;
        public int dwUseCardNo;// 是否带ATM信息进行查询：0-不带ATM信息，1-按交易卡号查询，2-按交易类型查询，3-按交易金额查询，4-按卡号、交易类型及交易金额的组合查询
        // 5-按课程名称查找，此时卡号表示课程名称
        public byte[] sCardNumber = new byte[CARDNUM_LEN_OUT];
        public NET_DVR_TIME struStartTime = new NET_DVR_TIME();
        public NET_DVR_TIME struStopTime = new NET_DVR_TIME();
        public byte byDrawFrame; // 0:不抽帧，1：抽帧
        public byte byFindType; // 0:查询普通卷，1：查询存档卷
        public byte byQuickSearch; // 0:普通查询，1：快速（日历）查询
        public byte bySpecialFindInfoType; // 专有查询条件类型 0-无效， 1-带ATM查询条件
        public int dwVolumeNum; // 存档卷号
        public byte[] byWorkingDeviceGUID = new byte[GUID_LEN]; // 工作机GUID，通过获取N+1得到
        public NET_DVR_SPECIAL_FINDINFO_UNION uSpecialFindInfo = new NET_DVR_SPECIAL_FINDINFO_UNION(); // 专有查询条件
        public byte byStreamType; // 0-同一个时间段只返回一种录像，优先级顺序为：主码流、子码流、三码流，1-子码流，2-三码流，3-主码流，254-双码流搜索
        // (优先返回主码流录像，没有主码流录像时返回子码流录像)
        public byte byAudioFile; // 音频文件 0-非音频文件，1-音频文件
        public byte[] byRes2 = new byte[30]; // 保留
    }

    class NET_DVR_SPECIAL_FINDINFO_UNION extends Union {
        public byte[] byLenth = new byte[8];
        public NET_DVR_ATMFINDINFO struATMFindInfo = new NET_DVR_ATMFINDINFO(); // ATM查询
    }

    class NET_DVR_ATMFINDINFO extends Structure {
        public byte byTransactionType; // 交易类型 0-全部，1-查询， 2-取款， 3-存款， 4-修改密码，5-转账， 6-无卡查询 7-无卡存款， 8-吞钞 9-吞卡 10-自定义
        public byte[] byRes = new byte[3]; // 保留
        public int dwTransationAmount; // 交易金额 ;
    }

    // NET_DVR_Login_V40()参数

    class NET_DVR_LOCAL_SDK_PATH extends Structure {
        public byte[] sPath = new byte[NET_SDK_MAX_FILE_PATH];// 组件库地址
        public byte[] byRes = new byte[128];
    }

    // NET_DVR_Login_V30()参数结构
    class NET_DVR_DEVICEINFO_V30 extends Structure {
        public byte[] sSerialNumber = new byte[SERIALNO_LEN]; // 序列号
        public byte byAlarmInPortNum; // 报警输入个数
        public byte byAlarmOutPortNum; // 报警输出个数
        public byte byDiskNum; // 硬盘个数
        public byte byDVRType; // 设备类型, 1:DVR 2:ATM DVR 3:DVS ......
        public byte byChanNum; // 模拟通道个数
        public byte byStartChan; // 起始通道号,例如DVS-1,DVR - 1
        public byte byAudioChanNum; // 语音通道数
        public byte byIPChanNum; // 最大数字通道个数，低位
        public byte byZeroChanNum; // 零通道编码个数 //2010-01-16
        public byte byMainProto; // 主码流传输协议类型 0-private, 1-rtsp,2-同时支持private和rtsp
        public byte bySubProto; // 子码流传输协议类型0-private, 1-rtsp,2-同时支持private和rtsp
        public byte bySupport; // 能力，位与结果为0表示不支持，1表示支持，
        public byte bySupport1; // 能力集扩充，位与结果为0表示不支持，1表示支持
        public byte bySupport2; /* 能力 */
        public short wDevType; // 设备型号
        public byte bySupport3; // 能力集扩展
        public byte byMultiStreamProto;// 是否支持多码流,按位表示,0-不支持,1-支持,bit1-码流3,bit2-码流4,bit7-主码流，bit-8子码流
        public byte byStartDChan; // 起始数字通道号,0表示无效
        public byte byStartDTalkChan; // 起始数字对讲通道号，区别于模拟对讲通道号，0表示无效
        public byte byHighDChanNum; // 数字通道个数，高位
        public byte bySupport4; // 能力集扩展
        public byte byLanguageType;// 支持语种能力,按位表示,每一位0-不支持,1-支持
        // byLanguageType 等于0 表示 老设备
        // byLanguageType & 0x1表示支持中文
        // byLanguageType & 0x2表示支持英文
        public byte byVoiceInChanNum; // 音频输入通道数
        public byte byStartVoiceInChanNo; // 音频输入起始通道号 0表示无效
        public byte bySupport5;
        public byte bySupport6; // 能力
        public byte byMirrorChanNum; // 镜像通道个数，<录播主机中用于表示导播通道>
        public short wStartMirrorChanNo; // 起始镜像通道号
        public byte bySupport7; // 能力
        public byte byRes2; // 保留
    }

    class NET_DVR_TIME extends Structure {// 校时结构参数
        public int dwYear; // 年
        public int dwMonth; // 月
        public int dwDay; // 日
        public int dwHour; // 时
        public int dwMinute; // 分
        public int dwSecond; // 秒

        @Override
        public String toString() {
            return "NET_DVR_TIME.dwYear: " + dwYear + "\n" + "NET_DVR_TIME.dwMonth: \n" + dwMonth + "\n" +
                    "NET_DVR_TIME.dwDay: \n" + dwDay + "\n" + "NET_DVR_TIME.dwHour: \n" + dwHour + "\n" +
                    "NET_DVR_TIME" + ".dwMinute: \n" + dwMinute + "\n" + "NET_DVR_TIME.dwSecond: \n" + dwSecond;
        }

        // 用于列表中显示
        public String toStringTime() {
            return String.format("%02d/%02d/%02d%02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }

        // 存储文件名使用
        public String toStringTitle() {
            return String.format("Time%02d%02d%02d%02d%02d%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }
    }
}