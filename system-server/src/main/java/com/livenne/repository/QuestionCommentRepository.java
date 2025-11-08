package com.livenne.repository;

import com.livenne.common.model.QuestionComment;

import java.util.List;

public interface QuestionCommentRepository {
    QuestionComment findById(Long commentId);
    QuestionComment save(QuestionComment questionComment);
    void delete(Long commentId);
    void update(QuestionComment questionComment);
    List<QuestionComment> findAll(Long questionId);
}
