package com.livenne.service;

import com.livenne.common.model.CourseItem;

import java.util.List;

public interface CourseItemService {
    CourseItem add(CourseItem courseItem);
    void update(CourseItem courseItem);
    void delete(Long itemId);
    CourseItem get(Long itemId);
    List<CourseItem> getListByCourseId(Long courseId);
}
