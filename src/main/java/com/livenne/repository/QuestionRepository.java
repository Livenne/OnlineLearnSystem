package com.livenne.repository;

import io.github.livenne.BaseMapper;
import com.livenne.common.model.dto.QuestionDTO;
import com.livenne.common.model.entity.Question;

import java.util.List;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Insert;
import io.github.livenne.annotation.orm.Query;

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
