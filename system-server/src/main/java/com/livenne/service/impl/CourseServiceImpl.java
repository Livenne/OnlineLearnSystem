package com.livenne.service.impl;

import com.livenne.common.model.Course;
import com.livenne.repository.CourseRepository;
import com.livenne.repository.impl.CourseRepositoryImpl;
import com.livenne.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    public static CourseServiceImpl instance = new CourseServiceImpl();
    private CourseRepository courseRepository = CourseRepositoryImpl.instance;

    @Override
    public Course add(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void update(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void delete(Long courseId) {
        courseRepository.delete(courseId);
    }

    @Override
    public Course get(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Course getByCourseName(String courseName) {
        return courseRepository.findByName(courseName);
    }

    @Override
    public List<Course> getList() {
        return courseRepository.findAll();
    }

}
