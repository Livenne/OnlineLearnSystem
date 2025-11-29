package com.livenne.service;

import com.livenne.common.model.Question;

import java.util.List;

public interface QuestionService {
    Question getById(Long id);
    Question getByName(String name);
    List<Question> getAll();
    List<Question> getByIdList(List<Long> idList);
    List<Question> getByNameList(List<String> nameList);

    List<Question> getByUserId(Long userId);
}
