package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.annotation.Cond;
import com.livenne.annotation.Query;
import com.livenne.annotation.Repository;
import com.livenne.common.model.entity.Chapter;

import java.util.List;

@Repository
public interface ChapterRepository extends BaseMapper<Chapter> {
    @Query
    Chapter getById(@Cond("id") Long id);

    @Query
    List<Chapter> getByCourseId(@Cond("courseId") Long courseId);
}
