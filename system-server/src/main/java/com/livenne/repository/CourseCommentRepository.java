package com.livenne.repository;

import com.livenne.common.model.CourseComment;

import java.util.List;

public interface CourseCommentRepository {
    CourseComment findById(Long commentId);
    List<CourseComment> findAll(Long courseId);
    void delete(Long commentId);
    void update(CourseComment courseComment);
    CourseComment save(CourseComment courseComment);
}
