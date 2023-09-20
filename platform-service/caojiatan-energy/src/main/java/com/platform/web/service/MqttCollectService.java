package com.platform.web.service;

import com.platform.web.mqtt.ChannelName;
import com.platform.web.mqtt.HexConverUtil;
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
    public void mqtt2Redis(Message<byte[]> in) {
        String topic = in.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();

        String message = HexConverUtil.hexInPayload2String(in.getPayload());

        log.info("message-->{}", message);

        if (topic.equals(inputTopic)) {

        }

    }
}