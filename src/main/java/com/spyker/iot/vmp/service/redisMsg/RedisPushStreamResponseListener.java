package com.spyker.iot.vmp.service.redisMsg;

import com.alibaba.fastjson2.JSON;

import com.spyker.iot.vmp.service.bean.MessageForPushChannelResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 接收redis返回的推流结果
 *
 * @author lin
 */
@Component
public class RedisPushStreamResponseListener implements MessageListener {

    private static final Logger logger =
            LoggerFactory.getLogger(RedisPushStreamResponseListener.class);

    private final ConcurrentLinkedQueue<Message> taskQueue = new ConcurrentLinkedQueue<>();
    private final Map<String, PushStreamResponseEvent> responseEvents = new ConcurrentHashMap<>();
    @Autowired private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        logger.info("[REDIS消息-请求推流结果]： {}", new String(message.getBody()));
        boolean isEmpty = taskQueue.isEmpty();
        taskQueue.offer(message);
        if (isEmpty) {
            taskExecutor.execute(
                    () -> {
                        while (!taskQueue.isEmpty()) {
                            Message msg = taskQueue.poll();
                            try {
                                MessageForPushChannelResponse response =
                                        JSON.parseObject(
                                                new String(msg.getBody()),
                                                MessageForPushChannelResponse.class);
                                if (response == null
                                        || ObjectUtils.isEmpty(response.getApp())
                                        || ObjectUtils.isEmpty(response.getStream())) {
                                    logger.info("[REDIS消息-请求推流结果]：参数不全");
                                    continue;
                                }
                                // 查看正在等待的invite消息
                                if (responseEvents.get(response.getApp() + response.getStream())
                                        != null) {
                                    responseEvents
                                            .get(response.getApp() + response.getStream())
                                            .run(response);
                                }
                            } catch (Exception e) {
                                logger.warn(
                                        "[REDIS消息-请求推流结果] 发现未处理的异常, \r\n{}",
                                        JSON.toJSONString(message));
                                logger.error("[REDIS消息-请求推流结果] 异常内容： ", e);
                            }
                        }
                    });
        }
    }

    public void addEvent(String app, String stream, PushStreamResponseEvent callback) {
        responseEvents.put(app + stream, callback);
    }

    public void removeEvent(String app, String stream) {
        responseEvents.remove(app + stream);
    }

    public interface PushStreamResponseEvent {
        void run(MessageForPushChannelResponse response);
    }
}