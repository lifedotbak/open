package com.spyker.framework.aliyun.oss.vod;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.aliyuncs.vod.model.v20170321.*;
import com.spyker.framework.aliyun.oss.AliyunRamProperties;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

@Slf4j
@AutoConfiguration
@ConditionalOnClass(AliyunRamProperties.class)
public class AcsClientUtils {

    private static final String END_POINT = "sts.aliyuncs.com";
    private static final String REGION_ID = "cn-shanghai";

    @Autowired private AliyunRamProperties aliyunRamProperties;

    /**
     * 获取播放凭证
     *
     * @param videoid
     * @return
     */
    public GetVideoPlayAuthResponse getVideoPlayAuth(String videoid) {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoid);
        GetVideoPlayAuthResponse response = null;
        try {

            DefaultAcsClient aliyunClient =
                    new DefaultAcsClient(
                            DefaultProfile.getProfile(
                                    REGION_ID,
                                    aliyunRamProperties.getAccessKeyId(),
                                    aliyunRamProperties.getAccessKeySecret()));

            response = aliyunClient.getAcsResponse(request);

            log.info(response.getPlayAuth());

        } catch (ClientException e) {
            log.error("Failed：");
            log.error("Error code: " + e.getErrCode());
            log.error("Error message: " + e.getErrMsg());
            log.error("RequestId: " + e.getRequestId());

            log.error("error -->{}", e);
        }

        return response;
    }

    /**
     * 获取视频播放地址
     *
     * @param videoid
     * @return
     */
    public GetPlayInfoResponse getVideoPlayInfo(String videoid) {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(videoid);
        GetPlayInfoResponse response = null;
        try {

            DefaultAcsClient aliyunClient =
                    new DefaultAcsClient(
                            DefaultProfile.getProfile(
                                    REGION_ID,
                                    aliyunRamProperties.getAccessKeyId(),
                                    aliyunRamProperties.getAccessKeySecret()));

            response = aliyunClient.getAcsResponse(request);

        } catch (ClientException e) {
            log.error("Failed：");
            log.error("Error code: " + e.getErrCode());
            log.error("Error message: " + e.getErrMsg());
            log.error("RequestId: " + e.getRequestId());

            log.error("error -->{}", e);
        }

        return response;
    }

    /**
     * 获取源视频播放地址
     *
     * @param videoid
     * @return
     */
    public GetMezzanineInfoResponse getSourceVideoPlayInfo(String videoid) {
        GetMezzanineInfoRequest request = new GetMezzanineInfoRequest();
        request.setVideoId(videoid);
        GetMezzanineInfoResponse response = null;
        try {

            DefaultAcsClient aliyunClient =
                    new DefaultAcsClient(
                            DefaultProfile.getProfile(
                                    REGION_ID,
                                    aliyunRamProperties.getAccessKeyId(),
                                    aliyunRamProperties.getAccessKeySecret()));

            response = aliyunClient.getAcsResponse(request);

        } catch (ClientException e) {
            log.error("Failed：");
            log.error("Error code: " + e.getErrCode());
            log.error("Error message: " + e.getErrMsg());
            log.error("RequestId: " + e.getRequestId());

            log.error("error -->{}", e);
        }

        return response;
    }

    /**
     * STS临时授权访问
     *
     * @return
     */
    public AssumeRoleResponse assumeRole() {

        AssumeRoleResponse response = null;
        try {
            // Init ACS Client
            DefaultProfile.addEndpoint("", "", "Sts", END_POINT);
            IClientProfile profile =
                    DefaultProfile.getProfile(
                            "",
                            aliyunRamProperties.getAccessKeyId(),
                            aliyunRamProperties.getAccessKeySecret());
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(aliyunRamProperties.getRoleArn());
            request.setRoleSessionName(aliyunRamProperties.getRoleSessionName());
            // request.setPolicy(policy); // Optional
            response = client.getAcsResponse(request);

            log.info("response--->{}", response);
        } catch (ClientException e) {
            log.error("Failed：");
            log.error("Error code: " + e.getErrCode());
            log.error("Error message: " + e.getErrMsg());
            log.error("RequestId: " + e.getRequestId());

            log.error("error -->{}", e);
        }

        return response;
    }

    /**
     * 获取视频上传路径
     *
     * @param uploadVideoInfo
     * @return
     */
    public CreateUploadVideoResponse getAcsResponse(UploadVideoInfo uploadVideoInfo) {
        DefaultAcsClient aliyunClient =
                new DefaultAcsClient(
                        DefaultProfile.getProfile(
                                REGION_ID,
                                aliyunRamProperties.getAccessKeyId(),
                                aliyunRamProperties.getAccessKeySecret()));

        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        CreateUploadVideoResponse response = null;
        try {
            /*
             * 必选，视频源文件名称（必须带后缀, 支持 ".3gp", ".asf", ".avi", ".dat", ".dv", ".flv", ".f4v",
             * ".gif", ".m2t", ".m3u8", ".m4v", ".mj2", ".mjpeg", ".mkv", ".mov", ".mp4",
             * ".mpe", ".mpg", ".mpeg", ".mts", ".ogg", ".qt", ".rm", ".rmvb", ".swf",
             * ".ts", ".vob", ".wmv", ".webm"".aac", ".ac3", ".acm", ".amr", ".ape", ".caf",
             * ".flac", ".m4a", ".mp3", ".ra", ".wav", ".wma"）
             */
            request.setFileName(uploadVideoInfo.getFileName());
            // 必选，视频标题
            request.setTitle(uploadVideoInfo.getTitle());
            // 可选，分类ID
            request.setCateId(uploadVideoInfo.getCateId());
            // 可选，视频标签，多个用逗号分隔
            request.setTags(uploadVideoInfo.getTags());
            // 可选，视频描述
            request.setDescription(uploadVideoInfo.getDescription());

            request.setTemplateGroupId(uploadVideoInfo.getTemplateGroupId());
            // request.setTranscodeMode(uploadVideoInfo.getTranscodeMode());
            request.setCoverURL(uploadVideoInfo.getCoverURL());
            request.setFileSize(uploadVideoInfo.getFileSize());

            response = aliyunClient.getAcsResponse(request);

        } catch (ClientException e) {

            log.error("Failed：");
            log.error("Error code: " + e.getErrCode());
            log.error("Error message: " + e.getErrMsg());
            log.error("RequestId: " + e.getRequestId());

            log.error("error -->{}", e);
        }

        return response;
    }
}
