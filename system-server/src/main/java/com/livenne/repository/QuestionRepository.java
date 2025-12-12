package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.annotation.*;
import com.livenne.common.model.dto.QuestionDTO;
import com.livenne.common.model.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends BaseMapper<Question> {
    @Query
    Question getById(@Cond("questionId") Long questionId);

    @Query
    List<Question> getAll();

    @Query
    List<Question> getByUserId(@Cond("userId") Long userId);

    @Insert
    Long save(QuestionDTO question);

}
