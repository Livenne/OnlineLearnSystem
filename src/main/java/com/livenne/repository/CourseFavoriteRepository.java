package com.livenne.repository;


import io.github.livenne.BaseMapper;
import com.livenne.common.model.dto.CourseFavoriteDTO;
import com.livenne.common.model.entity.CourseFavorite;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Delete;
import io.github.livenne.annotation.orm.Insert;
import io.github.livenne.annotation.orm.Query;

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
