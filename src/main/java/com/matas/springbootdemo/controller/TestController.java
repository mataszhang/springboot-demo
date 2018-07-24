package com.matas.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author matas
 * @date 2018/7/21 10:21
 * @email mataszhang@163.com
 */
@RestController
public class TestController {
    @RequestMapping
    public String test() {
        return "hello";
    }
}
