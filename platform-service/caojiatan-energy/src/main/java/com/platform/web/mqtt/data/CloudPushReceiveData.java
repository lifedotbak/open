package com.platform.web.mqtt.data;

import lombok.Data;

import java.util.List;

/**
 *cloud/push/{sn}/real/gzip
 */
@Data
public class CloudPushReceiveData {

    String sn;
    String date;
    String code;
    List<DataDetails> data;

    @Data
    public class DataDetails {
        String id;
        String value;
    }
}