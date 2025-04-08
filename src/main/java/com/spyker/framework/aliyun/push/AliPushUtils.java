package com.spyker.framework.aliyun.push;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.aliyuncs.utils.ParameterHelper;
import com.spyker.framework.aliyun.push.enums.PushMessageType;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AliPushUtils {

    private static void pushAndroid(PushMessage pushMessage) {

        IClientProfile profile =
                DefaultProfile.getProfile(
                        "cn-hangzhou",
                        pushMessage.getAccessKeyId(),
                        pushMessage.getAccessKeySecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);
        PushRequest pushRequest = new PushRequest();
        // 推送目标
        pushRequest.setAppKey(pushMessage.getAndroidKey());
        // 推送目标:DEVICE:按设备推送ALIAS: 按别名推送:ACCOUNT:按帐号推送 TAG:按标签推送; ALL: 广播推送
        pushRequest.setTarget(pushMessage.getPushTargetType().getType());
        pushRequest.setTargetValue(pushMessage.getTargetValue());
        pushRequest.setPushType(pushMessage.getPushMessageType().getType());
        pushRequest.setDeviceType("ALL"); // 设备类型 ANDROID iOS ALL.
        // 推送配置
        pushRequest.setTitle(pushMessage.getTitle()); // 消息的标题
        pushRequest.setBody(pushMessage.getBody()); // 消息的内容
        // 推送配置: iOS
        pushRequest.setIOSBadge(5); // iOS应用图标右上角角标
        pushRequest.setIOSMusic("default"); // iOS通知声音
        pushRequest.setIOSSubtitle(pushMessage.getIosSubtitle()); // iOS10通知副标题的内容
        pushRequest.setIOSNotificationCategory(
                pushMessage.getIosNotificationCategory()); // 指定iOS10通知Category
        pushRequest.setIOSMutableContent(true); // 是否允许扩展iOS通知内容

        pushRequest.setIOSApnsEnv("PRODUCT"); // iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV"
        // : 表示开发环境 "PRODUCT" : 表示生产环境
        pushRequest.setIOSRemind(
                true); // 消息推送时设备不在线（既与移动推送的服务端的长连接通道不通），则这条推送会做为通知，通过苹果的APNs通道送达一次。注意：离线消息转通知仅适用于生产环境
        pushRequest.setIOSRemindBody("iOSRemindBody"); // iOS消息转通知时使用的iOS通知内容，仅当iOSApnsEnv=PRODUCT
        // && iOSRemind为true时有效

        pushRequest.setIOSExtParameters(pushMessage.getExtParameters()); // 通知的扩展属性(注意
        // :
        // 该参数要以json
        // map的格式传入,否则会解析出错)

        // 推送配置: Android
        pushRequest.setAndroidNotifyType("BOTH"); // 通知的提醒方式 "VIBRATE" : 震动
        // "SOUND" : 声音 "BOTH" :
        // 声音和震动 NONE : 静音
        pushRequest.setAndroidNotificationBarType(1); // 通知栏自定义样式0-100
        pushRequest.setAndroidNotificationBarPriority(1); // 通知栏自定义样式0-100
        pushRequest.setAndroidNotificationChannel("1");
        pushRequest.setAndroidOpenType(pushMessage.getAndroidOpenType().getType()); // 点击通知后动作
        // "APPLICATION"
        // :
        // 打开应用
        // "ACTIVITY" :
        // 打开AndroidActivity "URL" :
        // 打开URL "NONE" : 无跳转
        pushRequest.setAndroidOpenUrl(pushMessage.getAndroidOpenUrl()); // Android收到推送后打开对应的url,
        // 仅当AndroidOpenType="URL"有效
        pushRequest.setAndroidActivity(
                pushMessage.getAndroidActivity()); // 设定通知打开的activity，仅当AndroidOpenType
        // ="Activity"有效
        pushRequest.setAndroidMusic("default"); // Android通知音乐
        pushRequest.setAndroidPopupActivity(
                pushMessage.getAndroidPopupActivity()); // 设置该参数后启动辅助弹窗功能,
        // 此处指定通知点击后跳转的Activity（辅助弹窗的前提条件：1.
        // 集成第三方辅助通道；2.
        // StoreOffline参数设为true）
        pushRequest.setAndroidPopupTitle(pushMessage.getAndroidPopupTitle());
        pushRequest.setAndroidPopupBody(pushMessage.getAndroidPopupBody());
        pushRequest.setAndroidExtParameters(pushMessage.getExtParameters()); // 设定通知的扩展属性。(注意
        // :
        // 该参数要以
        // json
        // map
        // 的格式传入,否则会解析出错)
        // 推送控制
        Date pushDate = new Date(System.currentTimeMillis()); // 30秒之间的时间点,
        // 也可以设置成你指定固定时间
        String pushTime = ParameterHelper.getISO8601Time(pushDate);
        pushRequest.setPushTime(pushTime); // 延后推送。可选，如果不设置表示立即推送
        String expireTime =
                ParameterHelper.getISO8601Time(
                        new Date(System.currentTimeMillis() + 12 * 3600 * 1000));
        // 12小时后消息失效,
        // 不会再发送
        pushRequest.setExpireTime(expireTime);
        pushRequest.setStoreOffline(true); // 离线消息是否保存,若保存,
        // 在推送时候，用户即使不在线，下一次上线则会收到
        PushResponse pushResponse;
        try {
            pushResponse = client.getAcsResponse(pushRequest);

            log.info(
                    "MessageId = "
                            + pushResponse.getMessageId()
                            + ",RequestId = "
                            + pushResponse.getRequestId());

        } catch (Exception e) {
            log.error("error ==> {}", e);
        }
    }

    private static void pushIos(PushMessage pushMessage) {

        IClientProfile profile =
                DefaultProfile.getProfile(
                        "cn-hangzhou",
                        pushMessage.getAccessKeyId(),
                        pushMessage.getAccessKeySecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);
        PushRequest pushRequest = new PushRequest();
        // 推送目标
        pushRequest.setAppKey(pushMessage.getIosKey());
        // 推送目标:DEVICE:按设备推送ALIAS: 按别名推送:ACCOUNT:按帐号推送 TAG:按标签推送; ALL: 广播推送
        pushRequest.setTarget(pushMessage.getPushTargetType().getType());
        pushRequest.setTargetValue(pushMessage.getTargetValue());
        pushRequest.setPushType(pushMessage.getPushMessageType().getType());
        pushRequest.setDeviceType("ALL"); // 设备类型 ANDROID iOS ALL.
        // 推送配置
        pushRequest.setTitle(pushMessage.getTitle()); // 消息的标题
        pushRequest.setBody(pushMessage.getBody()); // 消息的内容
        // 推送配置: iOS
        pushRequest.setIOSBadge(5); // iOS应用图标右上角角标
        pushRequest.setIOSMusic("default"); // iOS通知声音
        pushRequest.setIOSSubtitle(pushMessage.getIosSubtitle()); // iOS10通知副标题的内容
        pushRequest.setIOSNotificationCategory(
                pushMessage.getIosNotificationCategory()); // 指定iOS10通知Category
        pushRequest.setIOSMutableContent(true); // 是否允许扩展iOS通知内容

        pushRequest.setIOSApnsEnv("PRODUCT"); // iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV"
        // : 表示开发环境 "PRODUCT" : 表示生产环境
        pushRequest.setIOSRemind(
                true); // 消息推送时设备不在线（既与移动推送的服务端的长连接通道不通），则这条推送会做为通知，通过苹果的APNs通道送达一次。注意：离线消息转通知仅适用于生产环境
        pushRequest.setIOSRemindBody("iOSRemindBody"); // iOS消息转通知时使用的iOS通知内容，仅当iOSApnsEnv=PRODUCT
        // && iOSRemind为true时有效

        pushRequest.setIOSExtParameters(pushMessage.getExtParameters()); // 通知的扩展属性(注意
        // :
        // 该参数要以json
        // map的格式传入,否则会解析出错)

        // 推送配置: Android
        pushRequest.setAndroidNotifyType("BOTH"); // 通知的提醒方式 "VIBRATE" : 震动
        // "SOUND" : 声音 "BOTH" :
        // 声音和震动 NONE : 静音
        pushRequest.setAndroidNotificationBarType(1); // 通知栏自定义样式0-100
        pushRequest.setAndroidNotificationBarPriority(1); // 通知栏自定义样式0-100
        pushRequest.setAndroidNotificationChannel("1");
        pushRequest.setAndroidOpenType(pushMessage.getAndroidOpenType().getType()); // 点击通知后动作
        // "APPLICATION"
        // :
        // 打开应用
        // "ACTIVITY" :
        // 打开AndroidActivity "URL" :
        // 打开URL "NONE" : 无跳转
        pushRequest.setAndroidOpenUrl(pushMessage.getAndroidOpenUrl()); // Android收到推送后打开对应的url,
        // 仅当AndroidOpenType="URL"有效
        pushRequest.setAndroidActivity(
                pushMessage.getAndroidActivity()); // 设定通知打开的activity，仅当AndroidOpenType
        // ="Activity"有效
        pushRequest.setAndroidMusic("default"); // Android通知音乐
        pushRequest.setAndroidPopupActivity(
                pushMessage.getAndroidPopupActivity()); // 设置该参数后启动辅助弹窗功能,
        // 此处指定通知点击后跳转的Activity（辅助弹窗的前提条件：1.
        // 集成第三方辅助通道；2.
        // StoreOffline参数设为true）
        pushRequest.setAndroidPopupTitle(pushMessage.getAndroidPopupTitle());
        pushRequest.setAndroidPopupBody(pushMessage.getAndroidPopupBody());
        pushRequest.setAndroidExtParameters(pushMessage.getExtParameters()); // 设定通知的扩展属性。(注意
        // :
        // 该参数要以
        // json
        // map
        // 的格式传入,否则会解析出错)
        // 推送控制
        Date pushDate = new Date(System.currentTimeMillis()); // 30秒之间的时间点,
        // 也可以设置成你指定固定时间
        String pushTime = ParameterHelper.getISO8601Time(pushDate);
        pushRequest.setPushTime(pushTime); // 延后推送。可选，如果不设置表示立即推送
        String expireTime =
                ParameterHelper.getISO8601Time(
                        new Date(System.currentTimeMillis() + 12 * 3600 * 1000));
        // 12小时后消息失效,
        // 不会再发送
        pushRequest.setExpireTime(expireTime);
        pushRequest.setStoreOffline(true); // 离线消息是否保存,若保存,
        // 在推送时候，用户即使不在线，下一次上线则会收到
        PushResponse pushResponse;
        try {
            pushResponse = client.getAcsResponse(pushRequest);

            log.info(
                    "MessageId = "
                            + pushResponse.getMessageId()
                            + ",RequestId = "
                            + pushResponse.getRequestId());

        } catch (Exception e) {
            log.error("error ==> {}", e);
        }
    }

    public static void main(String[] args) {

        PushMessage pushMessage = new PushMessage();

        pushMessage.setAccessKeyId("xxx");
        pushMessage.setAccessKeySecret("xxx");
        pushMessage.setSignName("初集健康");
        pushMessage.setAndroidKey(333383909);
        pushMessage.setIosKey(333383919);

        pushMessage.setPushMessageType(PushMessageType.NOTICE);
        pushMessage.setTitle("这是一个title");
        pushMessage.setBody("这是一个body");
        pushMessage.setTargetValue("587A6F47DC3442B9BC3B4B72D88BF820");

        Map<String, String> ext = new HashMap<>();

        ext.put("1", "222");
        ext.put("2", "4444");
        pushMessage.setExtParametersMap(ext);

        AliPushUtils.push(pushMessage);
    }

    public static void push(PushMessage pushMessage) {
        pushIos(pushMessage);
        pushAndroid(pushMessage);
    }
}
