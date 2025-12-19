package com.livenne.repository;

import io.github.livenne.BaseMapper;
import com.livenne.common.model.dto.CourseLikeDTO;
import com.livenne.common.model.entity.CourseLike;

import java.util.List;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Delete;
import io.github.livenne.annotation.orm.Insert;
import io.github.livenne.annotation.orm.Query;

@Repository
public interface CourseLikeRepository extends BaseMapper<CourseLike> {
    @Query
    List<CourseLike> getByCourseId(@Cond("courseId") Long courseId);

    @Query
    CourseLike isLike(@Cond("courseId") Long courseId, @Cond("userId") Long userId);

    @Insert
    Long save(CourseLikeDTO courseLike);

    @Delete
    void delete(@Cond("id") Long id);
}
