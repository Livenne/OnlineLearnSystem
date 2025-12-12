package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.annotation.Cond;
import com.livenne.annotation.Insert;
import com.livenne.annotation.Query;
import com.livenne.annotation.Repository;
import com.livenne.common.model.dto.QuestionCommentDTO;
import com.livenne.common.model.entity.QuestionComment;

import java.util.List;

@Repository
public interface QuestionCommentRepository extends BaseMapper<QuestionComment> {

    @Query
    List<QuestionComment> getByQuestionId(@Cond("questionId") Long questionId);

    @Insert
    Long save(QuestionCommentDTO questionComment);

}
