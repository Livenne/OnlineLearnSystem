package com.livenne.repository;


import com.livenne.BaseMapper;
import com.livenne.annotation.*;
import com.livenne.common.model.dto.CourseFavoriteDTO;
import com.livenne.common.model.entity.CourseFavorite;

import java.util.List;

@Repository
public interface CourseFavoriteRepository extends BaseMapper<CourseFavorite> {
    @Query
    List<CourseFavorite> getByCourseId(@Cond("courseId") Long courseId);

    @Query
    List<CourseFavorite> getByUserId(@Cond("userId") Long userId);

    @Query
    CourseFavorite isFavorite(@Cond("courseId") Long courseId,@Cond("userId") Long userId);

    @Insert
    Long save(CourseFavoriteDTO courseFavorite);

    @Delete
    void delete(@Cond("id") Long id);
}
