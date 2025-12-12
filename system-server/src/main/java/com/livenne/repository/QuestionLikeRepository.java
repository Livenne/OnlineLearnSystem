package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.annotation.*;
import com.livenne.common.model.dto.QuestionLikeDTO;
import com.livenne.common.model.entity.QuestionLike;

import java.util.List;

@Repository
public interface QuestionLikeRepository extends BaseMapper<QuestionLike> {

    @Query
    List<QuestionLike> getByQuestionId(@Cond("questionId") Long questionId);

    @Query
    QuestionLike isLike(@Cond("questionId") Long questionId, @Cond("userId") Long userId);

    @Insert
    Long save(QuestionLikeDTO questionLike);

    @Delete
    void delete(@Cond("id") Long id);

}
