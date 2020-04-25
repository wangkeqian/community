package com.qianma.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianma.community.Model.Question;
import com.qianma.community.dto.QueUsrDTO;
import com.qianma.community.mapper.QuestionMapper;
import com.qianma.community.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        question.setCreator(SystemUtil.getLoginUser().getId());
        questionMapper.insert(question);
        return question.getId();
    }

    public List<QueUsrDTO> selectForIndex() {
        return questionMapper.selectForIndex();
    }

    public PageInfo<QueUsrDTO> getPageListData(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<QueUsrDTO> queUsrDTOS = questionMapper.selectForIndex();
        PageInfo<QueUsrDTO> pageInfo = new PageInfo<>(queUsrDTOS);
        return  pageInfo;
    }

    public QueUsrDTO getQueUsr(String id) {
        return questionMapper.getQueUsrById(id);
    }
}
