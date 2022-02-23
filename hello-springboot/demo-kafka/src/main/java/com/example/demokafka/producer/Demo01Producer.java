package com.example.demokafka.producer;


import com.example.demokafka.model.Demo01Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * @author LC
 * @Description:
 * @Date 2022/2/21 11:44 上午 星期一
 * @Version 1.0
 */
@Component
public class Demo01Producer {
    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        //新建一条消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        ListenableFuture<SendResult<Object, Object>> send = kafkaTemplate.send(Demo01Message.TOPIC, message);
        //kafkaTemplate
        return send.get();
    }
}