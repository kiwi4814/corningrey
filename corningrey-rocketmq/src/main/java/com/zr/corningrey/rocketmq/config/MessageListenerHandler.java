package com.zr.corningrey.rocketmq.config;

import cn.hutool.core.collection.CollUtil;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageListenerHandler implements MessageListenerConcurrently {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListenerHandler.class);
    private static String TOPIC = "DemoTopic";

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                    ConsumeConcurrentlyContext context) {
        if (CollUtil.isEmpty(msgs)) {
            LOGGER.info("receive blank msgs...");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = msgs.get(0);
        String msg = new String(messageExt.getBody());
        if (messageExt.getTopic().equals(TOPIC)) {
            // mock 消费逻辑
            mockConsume(msg);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private void mockConsume(String msg){
        LOGGER.info("receive msg: {}.", msg);
    }
}