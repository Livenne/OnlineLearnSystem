package com.livenne.repository;

import io.github.livenne.BaseMapper;
import com.livenne.common.model.entity.Chapter;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Query;
import io.github.livenne.annotation.orm.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends BaseMapper<Chapter> {
    @Query
    Chapter getById(@Cond("id") Long id);

    @Query
    List<Chapter> getByCourseId(@Cond("courseId") Long courseId);
}
