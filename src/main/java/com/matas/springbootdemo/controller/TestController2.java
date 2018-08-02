package com.matas.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author matas
 * @date 2018/8/2 17:47
 * @email mataszhang@163.com
 */
@Controller
@SessionAttributes("name")
public class TestController2 {
    @RequestMapping("hello")
    public String hello(Model model) {
        model.addAttribute("name", "jack");
        return "hello";
    }

    @RequestMapping("session")
    public String session(String name) {
        return "hello";
    }
}
