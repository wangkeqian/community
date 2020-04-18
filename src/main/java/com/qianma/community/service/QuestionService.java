package com.qianma.community.service;

import com.qianma.community.Model.Question;
import com.qianma.community.mapper.QuestionMapper;
import com.qianma.community.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/4/18
 */
@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    public Question getById(String id){
        return questionMapper.getById(id);
    }
    public String insert(Question question){
        question.setCreator(SystemUtil.getLoginUser());
        questionMapper.insert(question);
        return question.getId();
    }
}
