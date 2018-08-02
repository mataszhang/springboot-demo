package com.matas.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author matas
 * @date 2018/8/2 17:47
 * @email mataszhang@163.com
 */
@Controller
@SessionAttributes("name")
public class TestController2 {
    @RequestMapping("set")
    public String hello(Model model) {
        model.addAttribute("name", "jack");
        HttpServletRequest request = null;
        return "set";
    }

    @RequestMapping("session")
    public String session(@ModelAttribute("name") String nameInModel, @SessionAttribute("name") String nameInSession, Model model) {
        model.addAttribute("nameInModel", nameInModel);
        model.addAttribute("nameInSession", nameInSession);
        return "session";
    }
}
