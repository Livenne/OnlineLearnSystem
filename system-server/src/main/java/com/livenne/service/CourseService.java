package com.livenne.service;

import com.livenne.common.model.Course;

import java.util.List;

public interface CourseService {
    Course add(Course course);
    void update(Course course);
    void delete(Long courseId);
    Course get(Long courseId);
    Course getByCourseName(String courseName);
    List<Course> getList();

}
