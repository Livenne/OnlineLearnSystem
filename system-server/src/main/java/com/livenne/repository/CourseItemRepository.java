package com.livenne.repository;

import com.livenne.common.model.CourseItem;

import java.util.List;

public interface CourseItemRepository {
    CourseItem save(CourseItem courseItem);
    CourseItem findById(Long itemId);
    List<CourseItem> findAll(Long courseId);
    void delete(Long itemId);
    void update(CourseItem courseItem);
}
