package com.livenne.repository;

import com.livenne.common.model.CourseLike;

import java.util.List;

public interface CourseLikeRepository {
    CourseLike findById(Long id);
    List<CourseLike> findByCourseId(Long courseId);
    Boolean isLike(Long userId, Long courseId);
    CourseLike save(CourseLike courseLike);
    void delete(Long userId, Long courseId);
    void update(CourseLike courseLike);
}
