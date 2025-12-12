package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.MatchType;
import com.livenne.annotation.*;
import com.livenne.common.model.entity.Course;
import com.livenne.common.model.entity.User;

import java.util.List;

@Repository
public interface CourseRepository extends BaseMapper<Course> {
    @Query
    Course getById(@Cond("courseId") Long courseId);

    @Query
    List<Course> getAll();

    @Query
    List<Course> queryName(@Cond(value = "name", type = MatchType.LIKE) String name);
}
