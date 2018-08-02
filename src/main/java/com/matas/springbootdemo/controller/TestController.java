package com.matas.springbootdemo.controller;

import com.matas.springbootdemo.log.annnotation.SysLog;
import com.matas.springbootdemo.log.aspect.SysLogAspect;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author matas
 * @date 2018/7/21 10:21
 * @email mataszhang@163.com
 */
@RestController
public class TestController {
    @RequestMapping("test")
    @SysLog("测试日志")
    public String test(@RequestParam(required = false) String name, Model model) {
        SysLogAspect.addLog("张三执行了测试程序" + LocalDateTime.now());
        return "hello " + name;
    }
}
