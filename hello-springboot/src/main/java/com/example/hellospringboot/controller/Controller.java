package com.example.hellospringboot.controller;

import com.example.hellospringboot.service.Ihandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LC
 * @Description:
 * @Date 2022/2/18 3:10 下午 星期五
 * @Version 1.0
 */

@RestController
public class Controller {

    @Qualifier("ahandle")
    @Autowired
    private Ihandle ihandle;

    @RequestMapping("/formatter")
    public String formatter(){
        return ihandle.chenFormatter();
    }
}