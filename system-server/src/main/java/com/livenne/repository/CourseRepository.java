package com.livenne.repository;

import com.livenne.common.model.Course;

import java.util.List;

public interface CourseRepository {
    Course save(Course course);
    Course findById(Long id);
    Course findByName(String name);
    List<Course> findAll();
    void delete(Long id);
    void update(Course course);
}
