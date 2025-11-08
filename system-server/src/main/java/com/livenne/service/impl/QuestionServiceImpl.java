package com.livenne.service.impl;

import com.livenne.common.model.Question;
import com.livenne.repository.QuestionRepository;
import com.livenne.repository.impl.QuestionRepositoryImpl;
import com.livenne.service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    public static QuestionService instance = new QuestionServiceImpl();
    private QuestionRepository questionRepository = QuestionRepositoryImpl.instance;

    @Override
    public Question add(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void delete(Long questionId) {
        questionRepository.delete(questionId);
    }

    @Override
    public void update(Question question) {
        questionRepository.update(question);
    }

    @Override
    public Question get(Long questionId) {
        return questionRepository.findById(questionId);
    }

    @Override
    public List<Question> getList() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getListByUserId(Long userId) {
        return questionRepository.findAllByUserId(userId);
    }
}
