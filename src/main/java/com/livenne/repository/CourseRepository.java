package com.livenne.repository;

import io.github.livenne.BaseMapper;
import io.github.livenne.MatchType;
import com.livenne.common.model.entity.Course;

import java.util.List;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Query;

@Repository
public interface CourseRepository extends BaseMapper<Course> {
    @Query
    Course getById(@Cond("courseId") Long courseId);

    @Query
    List<Course> getAll();

    @Query
    List<Course> queryName(@Cond(value = "name", type = MatchType.LIKE) String name);
}
