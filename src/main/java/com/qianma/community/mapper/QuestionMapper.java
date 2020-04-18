package com.qianma.community.mapper;

import com.qianma.community.Model.Question;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    Question getById(@Param("id") String id);
    String insert(Question question);
}
