package com.livenne.repository;

import io.github.livenne.BaseMapper;
import com.livenne.common.model.dto.QuestionCommentDTO;
import com.livenne.common.model.entity.QuestionComment;

import java.util.List;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Insert;
import io.github.livenne.annotation.orm.Query;

@Repository
public interface QuestionCommentRepository extends BaseMapper<QuestionComment> {

    @Query
    List<QuestionComment> getByQuestionId(@Cond("questionId") Long questionId);

    @Insert
    Long save(QuestionCommentDTO questionComment);

}
