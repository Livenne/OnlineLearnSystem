package com.livenne.service;

import com.livenne.common.model.QuestionLike;

import java.util.List;

public interface QuestionLikeService {
    QuestionLike get(Long id);
    List<QuestionLike> getByQuestionId(Long questionId);
    Boolean isLike(Long userId, Long questionId);
    QuestionLike add(QuestionLike questionLike);
    void delete(Long userId, Long questionId);
    void update(QuestionLike questionLike);
}
