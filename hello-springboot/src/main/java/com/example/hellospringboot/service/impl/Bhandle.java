package com.example.hellospringboot.service.impl;

import com.example.hellospringboot.service.Ihandle;
import org.springframework.stereotype.Component;

/**
 * @author LC
 * @Description:
 * @Date 2022/2/18 3:20 下午 星期五
 * @Version 1.0
 */

@Component
public class Bhandle implements Ihandle {
    @Override
    public String chenFormatter() {
        return "Bhandle";
    }
}