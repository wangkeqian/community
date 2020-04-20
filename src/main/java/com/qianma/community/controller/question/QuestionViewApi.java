package com.qianma.community.controller.question;

import com.github.pagehelper.PageInfo;
import com.qianma.community.Model.Question;
import com.qianma.community.common.DataEntity;
import com.qianma.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/4/18
 */
@Controller
@RequestMapping("/question/view/*")
public class QuestionViewApi {
    @Autowired
    QuestionService questionService;

    @GetMapping("/search/{id}")
    @ResponseBody
    public Question searchQuestion(@PathVariable String id){
        return questionService.getById(id);
    }


}
