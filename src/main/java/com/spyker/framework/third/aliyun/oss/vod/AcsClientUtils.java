package com.spyker.framework.third.aliyun.oss.vod;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.aliyuncs.vod.model.v20170321.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcsClientUtils {

    private AcsClientUtils(){}

    private static final String END_POINT = "sts.aliyuncs.com";

    private static final String REGION_ID = "cn-shanghai";

    public static DefaultAcsClient getAcsClient(String accessKeyId, String accessKeySecret) {
        DefaultAcsClient acsClient =
                new DefaultAcsClient(
                        DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret));
        return acsClient;
    }

    /**
     * 获取播放凭证
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param videoid
     * @return
     */
    public static GetVideoPlayAuthResponse getVideoPlayAuth(
            String accessKeyId, String accessKeySecret, String videoid) {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoid);
        GetVideoPlayAuthResponse response = null;
        try {

            DefaultAcsClient aliyunClient =
                    new DefaultAcsClient(
                            DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret));

            response = aliyunClient.getAcsResponse(request);

            log.info(response.getPlayAuth());

        } catch (ClientException e) {
            log.error("error -->{}", e);
        }

        return response;
    }

    /**
     * 获取视频播放地址
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param videoid
     * @return
     */
    public static GetPlayInfoResponse getVideoPlayInfo(
            String accessKeyId, String accessKeySecret, String videoid) {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(videoid);
        GetPlayInfoResponse response = null;
        try {

            DefaultAcsClient aliyunClient =
                    new DefaultAcsClient(
                            DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret));

            response = aliyunClient.getAcsResponse(request);

        } catch (ServerException e) {
            log.error("error -->{}", e);
        } catch (ClientException e) {
            log.error("error -->{}", e);
        }

        return response;
    }

    /**
     * 获取源视频播放地址
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param videoid
     * @return
     */
    public static GetMezzanineInfoResponse getSourceVideoPlayInfo(
            String accessKeyId, String accessKeySecret, String videoid) {
        GetMezzanineInfoRequest request = new GetMezzanineInfoRequest();
        request.setVideoId(videoid);
        GetMezzanineInfoResponse response = null;
        try {

            DefaultAcsClient aliyunClient =
                    new DefaultAcsClient(
                            DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret));

            response = aliyunClient.getAcsResponse(request);

        } catch (ServerException e) {
            log.error("error -->{}", e);
        } catch (ClientException e) {
            log.error("error -->{}", e);
        }

        return response;
    }

    /**
     * STS临时授权访问
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param roleArn
     * @param roleSessionName
     * @return
     * @throws ClientException
     */
    public static AssumeRoleResponse assumeRole(
            String accessKeyId, String accessKeySecret, String roleArn, String roleSessionName)
            throws ClientException {

        AssumeRoleResponse response = null;
        try {
            // Init ACS Client
            DefaultProfile.addEndpoint("", "", "Sts", END_POINT);
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
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
     * @param accessKeyId
     * @param accessKeySecret
     * @param uploadVideoInfo
     * @return
     */
    public static CreateUploadVideoResponse getAcsResponse(
            String accessKeyId, String accessKeySecret, UploadVideoInfo uploadVideoInfo) {
        DefaultAcsClient aliyunClient =
                new DefaultAcsClient(
                        DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret));

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

        } catch (ServerException e) {
            log.error("CreateUploadVideoRequest Server Exception:");
            log.error("error -->{}", e);
        } catch (ClientException e) {
            log.error("CreateUploadVideoRequest Client Exception:");
            log.error("error -->{}", e);
        }

        return response;
    }
}