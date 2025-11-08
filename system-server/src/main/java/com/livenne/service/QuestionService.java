package com.livenne.service;

import com.livenne.common.model.Question;

import java.util.List;

public interface QuestionService {
    Question add(Question question);
    void delete(Long questionId);
    void update(Question question);
    Question get(Long questionId);
    List<Question> getList();
    List<Question> getListByUserId(Long userId);
}
