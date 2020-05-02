package com.qianma.community.controller;

import com.qianma.community.common.ApplicationContextProvider;
import com.qianma.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/5/1
 */
@Controller
public class RandomThread  extends Thread {

    private QuestionService questionService;

    public RandomThread() {

    }

    public RandomThread(String name) {
        super(name);
        this.questionService = ApplicationContextProvider.getBean(QuestionService.class);
    }

    @Override
    public void run() {
        questionService.setCount("268516b4-d5de-4708-9b57-1c7ce4e7dc53","view");
        System.out.println(Thread.currentThread().getName());
    }
}
