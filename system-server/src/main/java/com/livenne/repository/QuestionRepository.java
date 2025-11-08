package com.livenne.repository;

import com.livenne.common.model.Question;

import java.util.List;

public interface QuestionRepository {
    Question save(Question question);
    Question findById(Long id);
    List<Question> findAll();
    void delete(Long id);
    void update(Question question);
    List<Question> findAllByUserId(Long userId);
}
