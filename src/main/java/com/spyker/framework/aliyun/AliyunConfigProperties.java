package com.spyker.framework.aliyun;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** 阿里云基础配置信息 */
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(value = "aliyun", ignoreInvalidFields = true)
public class AliyunConfigProperties {

    private Push push;
    private Sms sms;
    private Oss oss;
    private Ram ram;
    private Tts tts;

    @Data
    public static class Oss {

        private String accessKeyId;
        private String accessKeySecret;
        private String bucketName;
        private String endPoint;
        private Out out;

        @Data
        private static class Out {
            private String imgUrl;
        }
    }

    @Data
    public static class Push {

        private String accessKeyId;
        private String accessKeySecret;
        private String signName;
        private String androidKey;
        private String iosKey;
    }

    @Data
    public static class Sms {

        private String accessKeyId;
        private String accessKeySecret;
        private String signName;
    }

    @Data
    public static class Ram {

        private String accessKeyId;
        private String accessKeySecret;
        private String roleArn;
        private String roleSessionName;
        private int durationSeconds;
    }

    @Data
    public static class Tts {

        private String accessKeyId;
        private String accessKeySecret;
    }
}
