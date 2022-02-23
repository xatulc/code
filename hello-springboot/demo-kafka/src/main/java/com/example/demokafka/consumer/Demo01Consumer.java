package com.example.demokafka.consumer;


import com.example.demokafka.model.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author LC
 * @Description:
 * @Date 2022/2/21 11:43 上午 星期一
 * @Version 1.0
 */
@Slf4j
@Component
public class Demo01Consumer {

    @KafkaListener(topics = Demo01Message.TOPIC, groupId = "demo01-consumer-group-" + Demo01Message.TOPIC)
    public void onMessage(Demo01Message message){
        log.info("[消费][onMessage][线程号]:{} 消息内容:{}]", Thread.currentThread().getId(),message);
    }
}