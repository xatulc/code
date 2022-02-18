package com.example.hellospringboot.demo1.service.impl;

import com.example.hellospringboot.demo1.service.Ihandle;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author LC
 * @Description:
 * @Date 2022/2/18 3:19 下午 星期五
 * @Version 1.0
 */
@Primary
@Component
public class Ahandle implements Ihandle {
    @Override
    public String chenFormatter() {
        return "Ahandle";
    }
}