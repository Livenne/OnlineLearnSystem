package com.livenne.service.impl;

import com.livenne.common.model.QuestionComment;
import com.livenne.repository.QuestionCommentRepository;
import com.livenne.repository.impl.QuestionCommentRepositoryImpl;
import com.livenne.service.QuestionCommentService;

import java.util.List;

public class QuestionCommentServiceImpl implements QuestionCommentService{

    public static QuestionCommentService instance = new QuestionCommentServiceImpl();
    private final QuestionCommentRepository questionCommentRepository = QuestionCommentRepositoryImpl.instance;

    @Override
    public QuestionComment add(QuestionComment questionComment) {
        return questionCommentRepository.save(questionComment);
    }

    @Override
    public void delete(Long commentId) {
        questionCommentRepository.delete(commentId);
    }

    @Override
    public void update(QuestionComment questionComment) {
        questionCommentRepository.update(questionComment);
    }

    @Override
    public QuestionComment get(Long commentId) {
        return questionCommentRepository.findById(commentId);
    }

    @Override
    public List<QuestionComment> getListByQuestionId(Long questionId) {
        return questionCommentRepository.findAll(questionId);
    }
}
