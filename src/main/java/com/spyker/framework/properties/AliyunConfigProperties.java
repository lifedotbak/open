package com.spyker.framework.properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(value = "aliyun", ignoreInvalidFields = true)
public class AliyunConfigProperties {

    private Push push;
    private Sms sms;
    private Oss oss;
    private Ram ram;

    @Data
    private static class Push {

        private String accessKeyId;
        private String accessKeySecret;
        private String signName;
        private String androidKey;
        private String iosKey;
    }

    @Data
    private static class Sms {

        private String accessKeyId;
        private String accessKeySecret;
        private String signName;
    }

    @Data
    private static class Oss {

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
    private static class Ram {

        private String accessKeyId;
        private String accessKeySecret;
        private String roleArn;
        private String roleSessionName;
        private int durationSeconds;
    }
}