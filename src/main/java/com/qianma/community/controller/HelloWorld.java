package com.qianma.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/3/21
 */
@Controller
public class HelloWorld {
    @GetMapping("hello")
    public String helloWorld(Model model){
        model.addAttribute("name","王克千");
        return "helloworld";
    }
}
