package com.livenne.service;

import com.livenne.common.model.Course;
import com.livenne.common.model.CourseItem;

import java.util.List;

public interface CourseService {
    Course getById(Long id);
    Course getByName(String name);
    List<Course> getAll();
    List<Course> getByIdList(List<Long> idList);
    List<Course> getByNameList(List<String> nameList);

    List<Course> getByUserId(Long userId);
}
