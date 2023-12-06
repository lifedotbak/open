package com.spyker.framework.third.hikvision.video;

import com.spyker.framework.third.hikvision.data.HCOpInfo;
import com.spyker.framework.third.hikvision.jna.HCNetSDK;
import com.spyker.framework.third.hikvision.jna.PlayCtrl;
import com.spyker.framework.third.hikvision.utils.CommonUtil;
import com.spyker.framework.util.date.ExDateUtils;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 摄像头设备控制类
 *
 * @author spyker
 */
@Slf4j
public class HCVideo {

    static FRealDataCallBack_V30Impl fRealDataCallBack;// 预览回调函数实现
    static FPlayDataCallBackImpl playBackCallBack; // 回放码流回调
    static int Count = 0;
    static boolean palybackFlay = false;
    static FileOutputStream outputStream;
    static IntByReference m_lPort = new IntByReference(-1);
    static FileOutputStream fileOutputStream = null;
    HCNetSDK hCNetSDK;
    PlayCtrl playControl;
    HCOpInfo hCOpInfo;
    Timer downloadtimer;// 下载用定时器
    Timer playbacktimer;// 回放用定时器
    int m_lLoadHandle;
    int iPlayBack; // 回放句柄

    public HCVideo(HCNetSDK hCNetSDK, PlayCtrl playControl, HCOpInfo hCOpInfo) {
        this.hCNetSDK = hCNetSDK;
        this.playControl = playControl;
        this.hCOpInfo = hCOpInfo;
    }

    /**
     * 获取实时流数据
     *
     * @param vodioFileName
     */
    public void realPlay(String vodioFileName) {

        int lPlay = openRealPlay();

        hCNetSDK.NET_DVR_SaveRealData(lPlay, vodioFileName);

        /**
         * 预览一段时间；如果要一直取流预览，需要保证程序一直运行
         */
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
            return;
        }

        if (lPlay >= 0) {
            if (hCNetSDK.NET_DVR_StopRealPlay(lPlay)) {

                log.info("止预览成功");
            }
        }
    }

    private int openRealPlay() {

        /**
         * 预览句柄
         */
        int lPlay = -1;

        int userID = hCOpInfo.getLUserID();
        int iChannelNo = hCOpInfo.getLDChannel();

        if (userID == -1) {

            log.error("请先注册");

            return lPlay;
        }
        HCNetSDK.NET_DVR_PREVIEWINFO strClientInfo = new HCNetSDK.NET_DVR_PREVIEWINFO();
        strClientInfo.read();
        strClientInfo.hPlayWnd = 0; // 窗口句柄，从回调取流不显示一般设置为空
        strClientInfo.lChannel = iChannelNo; // 通道号
        strClientInfo.dwStreamType = 0; // 0-主码流，1-子码流，2-三码流，3-虚拟码流，以此类推
        strClientInfo.dwLinkMode = 7; // 连接方式：0- TCP方式，1- UDP方式，2- 多播方式，3- RTP方式，4- RTP/RTSP，5- RTP/HTTP，6-
        // HRUDP（可靠传输） ，7- RTSP/HTTPS，8- NPQ
        strClientInfo.bBlocked = 1;
        strClientInfo.write();

        // 回调函数定义必须是全局的
        if (fRealDataCallBack == null) {
            fRealDataCallBack = new FRealDataCallBack_V30Impl();
        }

        // 开启预览
        lPlay = hCNetSDK.NET_DVR_RealPlay_V40(userID, strClientInfo, fRealDataCallBack, Pointer.NULL);

        if (lPlay == -1) {
            int iErr = hCNetSDK.NET_DVR_GetLastError();
            log.error("取流失败-->{}", iErr);
            return -1;
        } else {

            log.info("取流成功");
        }

        return lPlay;
    }

    /**
     * 取流解码过程中播放库从解码码流中抓图
     */
    public String getPicByPlayCtrl(String realPicPath) {

        openRealPlay();

        // 取流成功后，延时一段时间保证播放库解码开始
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("InterruptedException");
            return realPicPath;
        }

        IntByReference pWidth = new IntByReference(500);
        IntByReference pHieght = new IntByReference(500);
        boolean bFlag = playControl.PlayM4_GetPictureSize(m_lPort.getValue(), pWidth, pHieght);
        if (!bFlag) {

            log.error("获取失败：" + playControl.PlayM4_GetLastError(m_lPort.getValue()));
            return realPicPath;
        }

        IntByReference RealPicSize = new IntByReference(0);
        int picsize = pWidth.getValue() * pHieght.getValue() * 5;
        HCNetSDK.BYTE_ARRAY picByte = new HCNetSDK.BYTE_ARRAY(picsize);
        picByte.write();
        Pointer pByte = picByte.getPointer();
        boolean b_GetPic = playControl.PlayM4_GetJPEG(m_lPort.getValue(), pByte, picsize, RealPicSize);
        if (!b_GetPic) {

            log.error("抓图失败--->{}" + playControl.PlayM4_GetLastError(m_lPort.getValue()));
            return realPicPath;
        }

        picByte.read();

        FileOutputStream fout = null;
        try {

            fout = new FileOutputStream(realPicPath);

            // 将字节写入文件
            long offset = 0;
            ByteBuffer buffers = pByte.getByteBuffer(offset, RealPicSize.getValue());
            byte[] bytes = new byte[RealPicSize.getValue()];
            //            buffers.rewind();
            buffers.get(bytes);
            fout.write(bytes);
            fout.close();

            log.info("抓图成功!--->{}", realPicPath);
        } catch (IOException e) {

            log.error("获取失败--->{}", e);

            realPicPath = "";
        }

        return realPicPath;

    }

    /**
     * 按时间回放获取码流数据(.mp4)
     *
     * @param filePath
     * @param start
     * @param end
     */
    public void playBackBytime(String filePath, Date start, Date end) {

        File file = new File(filePath); // 保存回调函数的音频数据

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                log.error("error--->{}", e);
            }
        }
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            log.error("error--->{}", e);
        }
        HCNetSDK.NET_DVR_VOD_PARA net_dvr_vod_para = new HCNetSDK.NET_DVR_VOD_PARA();
        net_dvr_vod_para.dwSize = net_dvr_vod_para.size();
        net_dvr_vod_para.struIDInfo.dwChannel = hCOpInfo.getLDChannel(); // 通道号
        // 开始时间
        net_dvr_vod_para.struBeginTime = CommonUtil.getHkTime(start);

        // 停止时间
        net_dvr_vod_para.struEndTime = CommonUtil.getHkTime(end);

        net_dvr_vod_para.hWnd = null; // 回放的窗口句柄，若置为空，SDK仍能收到码流数据，但不解码显示
        net_dvr_vod_para.write();

        int iPlayBack = hCNetSDK.NET_DVR_PlayBackByTime_V40(hCOpInfo.getLUserID(), net_dvr_vod_para);
        if (iPlayBack <= -1) {

            log.error("按时间回放失败，错误码为--->{}", hCNetSDK.NET_DVR_GetLastError());
            palybackFlay = true;
            return;
        }

        // 开启取流
        boolean bCrtl = hCNetSDK.NET_DVR_PlayBackControl(iPlayBack, HCNetSDK.NET_DVR_PLAYSTART, 0, null);
        if (playBackCallBack == null) {
            playBackCallBack = new FPlayDataCallBackImpl();
        }
        boolean bRet = hCNetSDK.NET_DVR_SetPlayDataCallBack_V40(iPlayBack, playBackCallBack, Pointer.NULL);
        // 开始计时器
        Timer Playbacktimer = new Timer();// 新建定时器
        Playbacktimer.schedule(new PlaybackTask(), 0, end.getTime() - start.getTime());// 0秒后开始响应函数
    }

    /**
     * 按文件回放录像
     *
     * @param userID   用户ID
     * @param lChannel 通道号
     */
    public void playBackByfile(int userID, int lChannel) {
        File file = new File(System.getProperty("user.dir") + "\\Download\\Videodatabyfile.mp4"); // 保存回调函数的音频数据

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String strFileName = "";
        HCNetSDK.NET_DVR_FILECOND_V40 struFileCond = new HCNetSDK.NET_DVR_FILECOND_V40();
        struFileCond.read();
        struFileCond.lChannel = lChannel; // 通道号 NVR设备路数小于32路的起始通道号从33开始，依次增加
        struFileCond.byFindType = 0; // 录象文件类型 0=定时录像
        // 起始时间
        struFileCond.struStartTime.dwYear = 2023;
        struFileCond.struStartTime.dwMonth = 03;
        struFileCond.struStartTime.dwDay = 14;
        struFileCond.struStartTime.dwHour = 00;
        struFileCond.struStartTime.dwMinute = 00;
        struFileCond.struStartTime.dwSecond = 00;
        // 停止时间
        struFileCond.struStopTime.dwYear = 2023;
        struFileCond.struStopTime.dwMonth = 03;
        struFileCond.struStopTime.dwDay = 15;
        struFileCond.struStopTime.dwHour = 00;
        struFileCond.struStopTime.dwMinute = 30;
        struFileCond.struStopTime.dwSecond = 00;
        struFileCond.write();
        int FindFileHandle = hCNetSDK.NET_DVR_FindFile_V40(userID, struFileCond);

        System.out.println(FindFileHandle);

        if (FindFileHandle <= -1) {
            System.out.println("查找建立失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
        }

        while (true) {
            HCNetSDK.NET_DVR_FINDDATA_V40 struFindData = new HCNetSDK.NET_DVR_FINDDATA_V40();

            long State = hCNetSDK.NET_DVR_FindNextFile_V40(FindFileHandle, struFindData);
            if (State <= -1) {

                System.out.println("查找失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
                return;

            } else {
                if (State == 1000) // 获取文件信息成功
                {
                    struFindData.read();
                    strFileName = new String(struFindData.sFileName, StandardCharsets.UTF_8).trim();
                    System.out.println("文件名称：" + strFileName);
                    System.out.println("文件大小:" + struFindData.dwFileSize);
                    System.out.println("获取文件成功");
                    break;

                } else {
                    if (State == 1001) // 未查找到文件
                    {
                        System.out.println("未查找到文件");
                        break;

                    } else {
                        if (State == 1002) // 正在查找请等待
                        {
                            System.out.println("正在查找，请等待");
                            continue;

                        } else {
                            if (State == 1003) // 没有更多的文件，查找结束
                            {
                                System.out.println("没有更多的文件，查找结束");
                                break;

                            } else {
                                if (State == 1004) // 查找文件时异常
                                {

                                    System.out.println("没有更多的文件，查找结束");
                                    break;

                                } else {
                                    if (State == 1005) // 查找文件超时
                                    {

                                        System.out.println("没有更多的文件，查找结束");
                                        break;

                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        boolean b_CloseHandle = hCNetSDK.NET_DVR_FindClose_V30(FindFileHandle);
        if (!b_CloseHandle) {
            System.out.println("关闭失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
            return;
        }

        int lPlayByFileHandle = hCNetSDK.NET_DVR_PlayBackByName(userID, strFileName, null);
        if (lPlayByFileHandle <= -1) {
            System.out.println("按文件回放失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
            return;
        }
        IntByReference intP1 = new IntByReference(0);
        IntByReference intInlen = new IntByReference(0);
        boolean b_PlayBackStart = hCNetSDK.NET_DVR_PlayBackControl_V40(lPlayByFileHandle,
                                                                       HCNetSDK.NET_DVR_PLAYSTART,
                                                                       intP1.getPointer(),
                                                                       4,
                                                                       Pointer.NULL,
                                                                       intInlen);
        if (!b_PlayBackStart) {
            System.out.println("开始播放失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
            return;
        }
        if (playBackCallBack == null) {
            playBackCallBack = new FPlayDataCallBackImpl();
        }
        boolean bRet = hCNetSDK.NET_DVR_SetPlayDataCallBack_V40(lPlayByFileHandle, playBackCallBack, Pointer.NULL);
        while (true) {
            int Pos = hCNetSDK.NET_DVR_GetDownloadPos(lPlayByFileHandle);
            if (Pos != 100) {
                System.out.println("回放进度:" + Pos);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            } else {
                break;
            }
        }
        boolean b_Stop = hCNetSDK.NET_DVR_StopPlayBack(lPlayByFileHandle);
        if (!b_Stop) {
            System.out.println("停止回放失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
            return;
        }
        System.out.println("回放成功");

    }

    public boolean findExistVideoFile(int iChannelNo, Date start, Date end) {

        int userID = hCOpInfo.getLUserID();

        HCNetSDK.NET_DVR_FILECOND_V40 struFileCond = new HCNetSDK.NET_DVR_FILECOND_V40();
        struFileCond.read();
        struFileCond.lChannel = iChannelNo; // 通道号 NVR设备路数小于32路的起始通道号从33开始，依次增加
        struFileCond.byFindType = 0; // 录象文件类型 0=定时录像

        // 起始时间
        struFileCond.struStartTime = CommonUtil.getHkTime(start);
        // 停止时间
        struFileCond.struStopTime = CommonUtil.getHkTime(end);

        struFileCond.write();
        int FindFileHandle = hCNetSDK.NET_DVR_FindFile_V40(userID, struFileCond);

        String strFileName = "";

        boolean exist = false;

        while (true) {

            HCNetSDK.NET_DVR_FINDDATA_V40 struFindData = new HCNetSDK.NET_DVR_FINDDATA_V40();

            long State = hCNetSDK.NET_DVR_FindNextFile_V40(FindFileHandle, struFindData);

            if (State <= -1) {

                System.out.println("查找失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
                return exist;

            } else {
                if (State == 1000) // 获取文件信息成功
                {
                    struFindData.read();
                    strFileName = new String(struFindData.sFileName, StandardCharsets.UTF_8).trim();

                    log.info("文件名称--->{},文件大小--->{}", strFileName, struFindData.dwFileSize);

                    log.info("获取文件成功");

                    exist = true;

                    break;

                } else {
                    if (State == 1001) // 未查找到文件
                    {

                        log.info("未查找到文件");

                        break;

                    } else {
                        if (State == 1002) // 正在查找请等待
                        {

                            log.info("正在查找，请等待");
                            continue;

                        } else {
                            if (State == 1003) // 没有更多的文件，查找结束
                            {

                                log.info("没有更多的文件，查找结束");
                                break;

                            } else {
                                if (State == 1004) // 查找文件时异常
                                {

                                    log.info("没有更多的文件，查找结束");
                                    break;

                                } else {
                                    if (State == 1005) // 查找文件超时
                                    {

                                        log.info("没有更多的文件，查找结束");
                                        break;

                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        return exist;

    }

    /**
     * 按时间下载录像(不支持转码3GP格式)
     *
     * @param sFileName
     * @param startTime
     * @param endTime
     */
    public void downloadRecordByTime(String sFileName, Date startTime, Date endTime) {

        int userID = hCOpInfo.getLUserID();

        int dwChannel = hCOpInfo.getLDChannel();

        log.info("loginSuccess-->{}", hCOpInfo);

        HCNetSDK.NET_DVR_PLAYCOND net_dvr_playcond = new HCNetSDK.NET_DVR_PLAYCOND();
        net_dvr_playcond.read();
        net_dvr_playcond.dwChannel = dwChannel; // 通道号 NVR设备路数小于32路的起始通道号从33开始，依次增加
        // 开始时间
        net_dvr_playcond.struStartTime = CommonUtil.getHkTime(startTime);
        // 停止时间
        net_dvr_playcond.struStopTime = CommonUtil.getHkTime(endTime);
        net_dvr_playcond.write();

        //		String sFileName = "d://" + System.currentTimeMillis() + ".mp4";

        m_lLoadHandle = hCNetSDK.NET_DVR_GetFileByTime_V40(userID, sFileName, net_dvr_playcond);

        log.info("m_lLoadHandle---->{}", m_lLoadHandle);

        if (m_lLoadHandle >= 0) {

            hCNetSDK.NET_DVR_PlayBackControl(m_lLoadHandle, HCNetSDK.NET_DVR_PLAYSTART, 0, null);

            log.info("开始下载时间---->{}", ExDateUtils.getCurrentDate());

            downloadtimer = new Timer();// 新建定时器
            downloadtimer.schedule(new DownloadTask(), 0, 5000);// 0秒后开始响应函数

            log.info("DownloadTask m_lLoadHandle---->{}", m_lLoadHandle);
        } else {

            log.error("按时间下载失败 laste error --->{}", hCNetSDK.NET_DVR_GetLastError());
            return;
        }

        /**
         * 文件下载结束或者下载错误跳出循环
         */
        while (true) {

            if (-1 == m_lLoadHandle) {
                break;
            }

            try {
                Thread.sleep(1000);
                log.info("文件下载等待中----");
            } catch (InterruptedException e) {
                log.error("InterruptedException");
            }
        }

        log.info("op end---->{}", m_lLoadHandle);
    }

    /**
     * 获取DVR通道信息
     *
     * @param userID
     */
    public void getDvrIPChannelInfo(int userID) {
        IntByReference ibrBytesReturned = new IntByReference(0);// 获取IP接入配置参数
        HCNetSDK.NET_DVR_IPPARACFG_V40 m_strIpparaCfg = new HCNetSDK.NET_DVR_IPPARACFG_V40();
        m_strIpparaCfg.write();
        // lpIpParaConfig 接收数据的缓冲指针
        Pointer lpIpParaConfig = m_strIpparaCfg.getPointer();
        boolean bRet = hCNetSDK.NET_DVR_GetDVRConfig(userID,
                                                     HCNetSDK.NET_DVR_GET_IPPARACFG_V40,
                                                     0,
                                                     lpIpParaConfig,
                                                     m_strIpparaCfg.size(),
                                                     ibrBytesReturned);
        m_strIpparaCfg.read();

        log.info("起始数字通道号--->{}", m_strIpparaCfg.dwStartDChan);
        log.info("数字通道数目--->{}", m_strIpparaCfg.dwDChanNum);

        System.out.println(m_strIpparaCfg);

        for (int iChannum = 0; iChannum < m_strIpparaCfg.dwDChanNum; iChannum++) {
            int channum = iChannum + m_strIpparaCfg.dwStartDChan;
            m_strIpparaCfg.struStreamMode[iChannum].read();
            if (m_strIpparaCfg.struStreamMode[iChannum].byGetStreamType == 0) {
                m_strIpparaCfg.struStreamMode[iChannum].uGetStream.setType(HCNetSDK.NET_DVR_IPCHANINFO.class);
                m_strIpparaCfg.struStreamMode[iChannum].uGetStream.struChanInfo.read();
                if (m_strIpparaCfg.struStreamMode[iChannum].uGetStream.struChanInfo.byEnable == 1) {

                    log.info("IP通道{}在线", channum);
                } else {
                    log.info("IP通道{}不在线", channum);
                }
            }
        }
    }

    class PlaybackTask extends TimerTask {
        // 定时器函数
        @Override
        public void run() {
            System.out.println("定时器触发");
            IntByReference nPos = new IntByReference(0);

            System.out.println("iPlayBack " + iPlayBack);
            boolean bret = hCNetSDK.NET_DVR_PlayBackControl(iPlayBack, HCNetSDK.NET_DVR_PLAYGETPOS, 0, nPos);
            if (bret) {
                System.out.println("回放进度" + nPos.getValue());
            } else {
                System.out.println("获取回放进度失败");
            }

            if (nPos.getValue() > 100) {

                hCNetSDK.NET_DVR_StopPlayBack(iPlayBack);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                palybackFlay = true;
                System.out.println("由于网络原因或DVR忙,回放异常终止!");
                return;
            }
            if (nPos.getValue() == 100) {
                hCNetSDK.NET_DVR_StopPlayBack(iPlayBack);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                palybackFlay = true;
                System.out.println("按时间回放结束");

            }
        }

    }

    class FPlayDataCallBackImpl implements HCNetSDK.FPlayDataCallBack {

        @Override
        public void invoke(int lPlayHandle, int dwDataType, Pointer pBuffer, int dwBufSize, int dwUser) {
            log.info("回放码流回调...");
            // 将设备发送过来的回放码流数据写入文件
            long offset = 0;
            ByteBuffer buffers = pBuffer.getByteBuffer(offset, dwBufSize);
            byte[] bytes = new byte[dwBufSize];
            buffers.rewind();
            buffers.get(bytes);
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                log.error("error--->{}", e);
            }
        }

    }

    /*************************************************
     * 类: DownloadTask 类描述: 下载定时器响应函数
     *************************************************/
    class DownloadTask extends TimerTask {

        // 定时器函数
        @Override
        public void run() {

            IntByReference nPos = new IntByReference(0);
            hCNetSDK.NET_DVR_PlayBackControl(m_lLoadHandle, HCNetSDK.NET_DVR_PLAYGETPOS, 0, nPos);

            log.info("下载进度 --->{}", nPos.getValue() + "%");

            if (nPos.getValue() > 100) {
                m_lLoadHandle = -1;
                downloadtimer.cancel();

                log.error("由于网络原因或DVR忙,下载异常终止!");

            }
            if (nPos.getValue() == 100) {

                log.info("按时间下载结束!");

                m_lLoadHandle = -1;
                downloadtimer.cancel();

                if (hCNetSDK.NET_DVR_Logout(hCOpInfo.getLUserID())) {
                    log.info("注销成功!");
                }

            }

        }
    }

    class FRealDataCallBack_V30Impl implements HCNetSDK.FRealDataCallBack_V30 {

        // 预览回调
        @Override
        public void invoke(int lRealHandle, int dwDataType, ByteByReference pBuffer, int dwBufSize, Pointer pUser) {
            if (Count == 100) {// 降低打印频率
                System.out.println("码流数据回调...dwBufSize=" + dwBufSize);
                System.out.println("dwDataType=" + dwDataType);
                Count = 0;
            }

            Count++;
            // 播放库解码
            switch (dwDataType) {
                case HCNetSDK.NET_DVR_SYSHEAD: // 系统头

                    if (!playControl.PlayM4_GetPort(m_lPort)) // 获取播放库未使用的通道号
                    {
                        break;
                    }
                    if (dwBufSize > 0) {
                        if (!playControl.PlayM4_SetStreamOpenMode(m_lPort.getValue(), PlayCtrl.STREAME_REALTIME)) //
                        // 设置实时流播放模式
                        {
                            break;
                        }
                        if (!playControl.PlayM4_OpenStream(m_lPort.getValue(), pBuffer, dwBufSize, 1024 * 1024)) //
                        // 打开流接口
                        {
                            break;
                        }
                        if (!playControl.PlayM4_Play(m_lPort.getValue(), null)) // 播放开始
                        {
                            break;
                        }

                    }
                case HCNetSDK.NET_DVR_STREAMDATA: // 码流数据

                    if ((dwBufSize > 0) && (m_lPort.getValue() != -1)) {
                        if (!playControl.PlayM4_InputData(m_lPort.getValue(), pBuffer, dwBufSize)) // 输入流数据
                        {
                            break;
                        }
                    }
            }
        }
    }

}