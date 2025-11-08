package com.livenne.service.impl;

import com.livenne.common.model.QuestionLike;
import com.livenne.repository.QuestionLikeRepository;
import com.livenne.repository.impl.QuestionLikeRepositoryImpl;
import com.livenne.service.QuestionLikeService;

import java.util.List;

public class QuestionLikeServiceImpl implements QuestionLikeService {

    public static QuestionLikeService instance = new QuestionLikeServiceImpl();
    private final QuestionLikeRepository questionLikeRepository = QuestionLikeRepositoryImpl.instance;

    @Override
    public QuestionLike get(Long id) {
        return questionLikeRepository.findById(id);
    }

    @Override
    public List<QuestionLike> getByQuestionId(Long questionId) {
        return questionLikeRepository.findByQuestionId(questionId);
    }

    @Override
    public Boolean isLike(Long userId, Long questionId) {
        return questionLikeRepository.isLike(userId,questionId);
    }

    @Override
    public QuestionLike add(QuestionLike questionLike) {
        return questionLikeRepository.save(questionLike);
    }

    @Override
    public void delete(Long userId, Long questionId) {
        questionLikeRepository.delete(userId,questionId);
    }

    @Override
    public void update(QuestionLike questionLike) {
        questionLikeRepository.update(questionLike);
    }
}
