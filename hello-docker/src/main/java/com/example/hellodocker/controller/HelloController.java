package com.example.hellodocker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LC
 * @Description:
 * @Date 2022/2/9 5:41 下午 星期三
 * @Version 1.0
 */

@RestController
public class HelloController {
    @RequestMapping("/docker/hello")
    public String hello(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String now = df.format(new Date());
        System.out.println(now + "-欢迎访问：将一个springboot打包一个docker镜像");
        return now + "-欢迎访问：将一个springboot打包一个docker镜像";
    }
}