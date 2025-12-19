package com.livenne.repository;

import io.github.livenne.BaseMapper;
import com.livenne.common.model.dto.QuestionLikeDTO;
import com.livenne.common.model.entity.QuestionLike;

import java.util.List;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Delete;
import io.github.livenne.annotation.orm.Insert;
import io.github.livenne.annotation.orm.Query;

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
