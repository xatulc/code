package com.example.demokafka.model;

import lombok.Data;

/**
 * @author LC
 * @Description:
 * @Date 2022/2/21 11:46 上午 星期一
 * @Version 1.0
 */

@Data
public class Demo01Message {
    public static final String TOPIC = "DEMO01";
    private Integer id;
}