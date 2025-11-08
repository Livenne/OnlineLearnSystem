package com.livenne.repository;

import com.livenne.common.model.QuestionLike;

import java.util.List;

public interface QuestionLikeRepository {
    QuestionLike findById(Long id);
    List<QuestionLike> findByQuestionId(Long questionId);
    QuestionLike save(QuestionLike questionLike);
    Boolean isLike(Long userId, Long questionId);
    void delete(Long userId, Long questionId);
    void update(QuestionLike questionLike);
}
