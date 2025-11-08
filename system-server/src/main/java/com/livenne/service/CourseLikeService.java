package com.livenne.service;

import com.livenne.common.model.CourseLike;

import java.util.List;

public interface CourseLikeService{
    CourseLike get(Long id);
    Boolean isLike(Long userId, Long courseId);
    CourseLike add(CourseLike courseLike);
    void delete(Long userId, Long courseId);
    void update(CourseLike courseLike);
}
