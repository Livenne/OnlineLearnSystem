package com.livenne.service.impl;

import com.livenne.common.model.CourseLike;
import com.livenne.repository.CourseLikeRepository;
import com.livenne.repository.impl.CourseLikeRepositoryImpl;
import com.livenne.service.CourseLikeService;

import java.util.List;

public class CourseLikeServiceImpl implements CourseLikeService {

    public static CourseLikeService instance = new CourseLikeServiceImpl();
    private final CourseLikeRepository courseLikeRepository = CourseLikeRepositoryImpl.instance;

    @Override
    public CourseLike get(Long id) {
        return courseLikeRepository.findById(id);
    }

    @Override
    public Boolean isLike(Long userId, Long courseId) {
        return courseLikeRepository.isLike(userId, courseId);
    }

    @Override
    public CourseLike add(CourseLike courseLike) {
        return courseLikeRepository.save(courseLike);
    }

    @Override
    public void delete(Long userId,Long courseId) {
        courseLikeRepository.delete(userId,courseId);
    }

    @Override
    public void update(CourseLike courseLike) {
        courseLikeRepository.update(courseLike);
    }
}
