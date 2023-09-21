package com.platform.web.service;

import com.google.gson.Gson;
import com.platform.web.mqtt.ChannelName;
import com.platform.web.mqtt.data.CloudPushReceiveData;
import com.platform.web.utils.GZIPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MqttCollectService {

    @Value("${mqtt.topic.input}")
    private String inputTopic;// 订阅主题，可以是多个主题

    @ServiceActivator(inputChannel = ChannelName.INPUT_DATA)
    public void receive(Message<byte[]> in) {
        String topic = in.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();

        if (topic.contains("cloud/push")) {

            String result = GZIPUtils.uncompressToString(in.getPayload(), "UTF-8");
            log.info("result-->{}", result);

            Gson gson = new Gson();
            CloudPushReceiveData receiveData = gson.fromJson(result, CloudPushReceiveData.class);
            log.info("receiveData-->{}", receiveData);

        }

        //        String message = HexConverUtil.hexInPayload2String(in.getPayload());
        //        String message = new String(in.getPayload(), "UTF-8" );

        //        log.info("message-->{}", message);
        //
        //        String result = GZIPUtils.uncompressToString(message.getBytes(), "UTF-8");
        //
        //        log.info("result-->{}", result);
        //
        //        if (topic.equals(inputTopic)) {
        //
        //        }

    }
}