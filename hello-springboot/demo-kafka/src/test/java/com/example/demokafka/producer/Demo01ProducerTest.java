package com.example.demokafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.ExecutionException;

/**
 * @author LC
 * @Description:
 * @Date 2022/2/21 3:15 下午 星期一
 * @Version 1.0
 */

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class Demo01ProducerTest {
    @Autowired
    Demo01Producer producer;

    @Test
    public void demo01Test() throws ExecutionException, InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
    }
}