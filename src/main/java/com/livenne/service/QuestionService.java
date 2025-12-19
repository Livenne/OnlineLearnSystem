package com.livenne.service;

import com.livenne.common.model.dto.QuestionCommentDTO;
import com.livenne.common.model.dto.QuestionDTO;
import com.livenne.common.model.dto.QuestionLikeDTO;
import com.livenne.common.model.entity.Question;
import com.livenne.common.model.entity.QuestionComment;
import com.livenne.common.model.entity.QuestionLike;

import java.util.List;

public interface QuestionService {
    Question getById(Long id);
    List<Question> getAll();
    List<Question> getByIdList(List<Long> idList);
    List<Question> getByUserId(Long userId);
    Boolean isExistById(Long id);
    List<QuestionComment> getComment(Long id);

    Boolean like(QuestionLikeDTO questionLikeDTO);

    Boolean isLike(QuestionLikeDTO questionLikeDTO);

    Long likes(Long id);

    Long comment(Long questionId, QuestionCommentDTO questionCommentDTO);

    Long comments(Long id);

    Long question(QuestionDTO questionDTO, Long userId);
}
