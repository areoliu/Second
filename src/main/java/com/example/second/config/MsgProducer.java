package com.example.second.config;

import com.alibaba.fastjson.JSON;
import com.example.second.Constant;
import com.example.second.entity.QueueMessage;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMsg(QueueMessage queueMessage){
        rocketMQTemplate.syncSend(Constant.TOPIC, new Gson().toJson(queueMessage));
        log.info("MsgProducer 生产者 >>> " + queueMessage.getMsgText() +", msgId = " + queueMessage.getMsgId());
    }


}
