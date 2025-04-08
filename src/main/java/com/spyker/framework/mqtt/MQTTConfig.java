package com.spyker.framework.mqtt;

import lombok.extern.slf4j.Slf4j;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.event.MqttSubscribedEvent;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.AbstractMqttMessageHandler;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

/** * MQTT 数据转发服务 mqtt.services MQTT服务地址不配置时，不会启用该服务 检测mqtt.services这个参数是否配置，以确定是否启用MQTT服务 */
@EnableIntegration
@AutoConfiguration
@ConditionalOnProperty(prefix = "mqtt", name = "enabled", havingValue = "true")
@Slf4j
public class MQTTConfig implements ApplicationListener<ApplicationEvent> {

    //	@Value("${mqtt.appid}")
    private final String appid = "MQTT_APPID_" + MqttAsyncClient.generateClientId(); // 客户端ID

    @Value("${mqtt.topic.input}")
    private String[] inputTopic; // 订阅主题，可以是多个主题

    @Value("${mqtt.services}")
    private String[] mqttServices; // 服务器地址以及端口

    @Value("${mqtt.user}")
    private String user; // 用户名

    @Value("${mqtt.password}")
    private String password; // 密码

    @Value("${mqtt.KeepAliveInterval:300}")
    private Integer KeepAliveInterval; // 心跳时间,默认为5分钟

    @Value("${mqtt.CleanSession:false}")
    private Boolean CleanSession; // 是否不保持session,默认为session保持

    @Value("${mqtt.AutomaticReconnect:true}")
    private Boolean AutomaticReconnect; // 是否自动重联，默认为开启自动重联

    @Value("${mqtt.CompletionTimeout:30000}")
    private Long CompletionTimeout; // 连接超时,默认为30秒

    @Value("${mqtt.Qos:1}")
    private Integer Qos; // 通信质量，详见MQTT协议

    public MQTTConfig() {}

    /**
     * 向服务器发送数据管道绑定
     *
     * @param connectionFactory tcp连接工厂类
     * @return 消息管道对象
     */
    @Bean
    @ServiceActivator(inputChannel = ChannelName.OUTPUT_DATA_MQTT)
    public AbstractMqttMessageHandler MQTTOutAdapter(MqttPahoClientFactory connectionFactory) {

        log.info("appid -->{}", appid);

        // 创建一个新的出站管道，由于MQTT的发布与订阅是两个独立的连接，因此客户端的ID(即APPID）不能与订阅时所使用的ID一样，否则在服务端会认为是同一个客户端，而造成连接失败
        MqttPahoMessageHandler outGate =
                new MqttPahoMessageHandler(appid + "_put", connectionFactory);
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        converter.setPayloadAsBytes(true); // bytes类型接收
        outGate.setAsync(true);
        outGate.setCompletionTimeout(CompletionTimeout); // 设置连接超时时时
        outGate.setDefaultQos(Qos); // 设置通信质量
        outGate.setConverter(converter);
        return outGate;
    }

    /**
     * MQTT连接配置
     *
     * @return 连接工厂
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {

        log.info("mqttServices---->{}", mqttServices);

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory(); // 连接工厂类
        MqttConnectOptions options = new MqttConnectOptions(); // 连接参数
        options.setServerURIs(mqttServices); // 连接地址
        if (null != user) {
            options.setUserName(user); // 用户名
        }
        if (null != password) {
            options.setPassword(password.toCharArray()); // 密码
        }
        options.setKeepAliveInterval(KeepAliveInterval); // 心跳时间
        options.setAutomaticReconnect(AutomaticReconnect); // 断开是否自动重联
        options.setCleanSession(CleanSession); // 保持session
        // 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息。

        log.info("appid -->{}", appid);

        String willMessage = appid + " is offline";
        options.setWill("willTopic", willMessage.getBytes(), 2, false);
        factory.setConnectionOptions(options);
        return factory;
    }

    /**
     * 入站管道
     *
     * @param mqttPahoClientFactory
     * @return
     */
    @Bean
    public MessageProducerSupport mqttInput(MqttPahoClientFactory mqttPahoClientFactory) {

        log.info("appid -->{}", appid);

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        appid, mqttPahoClientFactory, inputTopic); // 建立订阅连接
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        converter.setPayloadAsBytes(true); // bytes类型接收
        adapter.setCompletionTimeout(CompletionTimeout); // 连接超时的时间
        adapter.setConverter(converter);
        adapter.setQos(Qos); // 消息质量
        adapter.setOutputChannelName(ChannelName.INPUT_DATA); // 输入管道名称
        return adapter;
    }

    /**
     * MQTT连接时调用的方法
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MqttSubscribedEvent) {
            String msg = "OK";
            /** ------------------连接时需要发送起始消息，写在这里------------- * */
            //	            msgSendService.send(MessageBuilder.withPayload(msg.getBytes()).build());
        }
    }
}
