package com.example.demokafka.controller;

import com.example.demokafka.producer.Demo01Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author LC
 * @Description:
 * @Date 2022/2/21 12:58 下午 星期一
 * @Version 1.0
 */

@RestController
@Slf4j
public class TestController {
    @Autowired
    private Demo01Producer producer;

    @RequestMapping("/ok")
    public void demo01Test() throws ExecutionException, InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
    }
}