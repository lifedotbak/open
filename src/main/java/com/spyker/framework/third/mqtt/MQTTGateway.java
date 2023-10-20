package com.spyker.framework.third.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 发送消息网关,其它需要发向服务器发送消息时，调用该接口
 *
 * @author spyker
 */
@MessagingGateway(defaultRequestChannel = ChannelName.OUTPUT_DATA_MQTT)
@Component
public interface MQTTGateway {

    /**
     * 发送信息到MQTT服务器
     *
     * @param payload 发送的文本
     */
    void sendToMqtt(String payload);

    /**
     * 发送信息到MQTT服务器
     *
     * @param topic 主题
     * @param bytes 消息主体
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, byte[] bytes);
    //    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    /**
     * 发送信息到MQTT服务器
     *
     * @param topic   主题
     * @param qos     对消息处理的几种机制。 0 表示的是订阅者没收到消息不会再次发送，消息会丢失。 1
     *                表示的是会尝试重试，一直到接收到消息，但这种情况可能导致订阅者收到多次重复消息。 2
     *                多了一次去重的动作，确保订阅者收到的消息有一次。
     * @param payload 消息主体
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

}