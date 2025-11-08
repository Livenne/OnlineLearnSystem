package com.livenne.service;

import com.livenne.common.model.QuestionComment;

import java.util.List;

public interface QuestionCommentService {
    QuestionComment add(QuestionComment questionComment);
    void delete(Long commentId);
    void update(QuestionComment questionComment);
    QuestionComment get(Long commentId);
    List<QuestionComment> getListByQuestionId(Long questionId);

}
