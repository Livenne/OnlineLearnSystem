package com.livenne.service;

import com.livenne.common.model.CourseComment;

import java.util.List;

public interface CourseCommentService {
    CourseComment add(CourseComment courseComment);
    void delete(Long commentId);
    void update(CourseComment courseComment);
    CourseComment get(Long commentId);
    List<CourseComment> getListByCourseId(Long courseId);
}
