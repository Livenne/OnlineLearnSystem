package com.livenne.service.impl;

import com.livenne.common.model.CourseComment;
import com.livenne.repository.CourseCommentRepository;
import com.livenne.repository.impl.CourseCommentRepositoryImpl;
import com.livenne.service.CourseCommentService;

import java.util.List;

public class CourseCommentServiceImpl implements CourseCommentService {

    public static CourseCommentService instance = new CourseCommentServiceImpl();
    private final CourseCommentRepository courseCommentRepository = CourseCommentRepositoryImpl.instance;

    @Override
    public CourseComment add(CourseComment courseComment) {
        return courseCommentRepository.save(courseComment);
    }

    @Override
    public void delete(Long commentId) {
        courseCommentRepository.delete(commentId);
    }

    @Override
    public void update(CourseComment courseComment) {
        courseCommentRepository.save(courseComment);
    }

    @Override
    public CourseComment get(Long commentId) {
        return courseCommentRepository.findById(commentId);
    }

    @Override
    public List<CourseComment> getListByCourseId(Long courseId) {
        return courseCommentRepository.findAll(courseId);
    }
}
