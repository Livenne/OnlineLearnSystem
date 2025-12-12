package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.annotation.*;
import com.livenne.common.model.dto.CourseLikeDTO;
import com.livenne.common.model.entity.CourseLike;

import java.util.List;

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
