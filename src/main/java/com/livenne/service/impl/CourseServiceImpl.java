package com.livenne.service.impl;

import com.livenne.common.model.dto.CourseCommentDTO;
import com.livenne.common.model.dto.CourseFavoriteDTO;
import com.livenne.common.model.dto.CourseLikeDTO;
import com.livenne.common.model.entity.*;
import com.livenne.repository.*;
import com.livenne.service.CourseService;
import io.github.livenne.annotation.container.Autowired;
import io.github.livenne.annotation.container.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserCourseRepository userCourseRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private UserOrderRepository userOrderRepository;
    @Autowired
    private CourseCommentRepository courseCommentRepository;
    @Autowired
    private CourseLikeRepository courseLikeRepository;
    @Autowired
    private CourseFavoriteRepository courseFavoriteRepository;

    @Override
    public Boolean isExistById(Long id) {
        Course course = getById(id);
        return course != null && course.getCourseId() != null;
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.getById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.getAll();
    }

    @Override
    public List<Course> getByIdList(List<Long> idList) {
        return idList.stream().map(this::getById).toList();
    }

    @Override
    public List<Course> queryByName(String name) {
        return courseRepository.queryName(name);
    }

    @Override
    public List<Course> getByUserId(Long userId) {
        return userCourseRepository.getByUserId(userId)
                .stream()
                .map(UserCourse::getCourseId)
                .map(this::getById)
                .toList();
    }

    @Override
    public List<Chapter> getChapter(Long id) {
        return chapterRepository.getByCourseId(id);
    }

    @Override
    public Long chapters(Long id) {
        return (long) getChapter(id).size();
    }

    @Override
    public Long purchase(Long id) {
        return (long) userOrderRepository.getByCourseId(id).size();
    }

    @Override
    public Long rating(Long id) {
        return (long) courseCommentRepository.getByCourseId(id)
                .stream()
                .mapToLong(CourseComment::getRating)
                .average().orElse(0.0);
    }

    @Override
    public List<CourseComment> getComment(Long id) {
        return courseCommentRepository.getByCourseId(id);
    }

    @Override
    public Long comments(Long id) {
        return (long) getChapter(id).size();
    }

    @Override
    public Long comment(Long courseId, CourseCommentDTO courseCommentDTO) {
        return courseCommentRepository.save(courseCommentDTO);
    }

    @Override
    public Boolean like(CourseLikeDTO courseLikeDTO) {
        CourseLike like = courseLikeRepository.isLike(courseLikeDTO.getCourseId(), courseLikeDTO.getUserId());
        if (isLike(courseLikeDTO)){
            courseLikeRepository.delete(like.getId());
            return false;
        }
        courseLikeRepository.save(courseLikeDTO);
        return true;
    }

    @Override
    public Boolean isLike(CourseLikeDTO courseLikeDTO) {
        CourseLike like = courseLikeRepository.isLike(courseLikeDTO.getCourseId(), courseLikeDTO.getUserId());
        return like != null && like.getId() != null;
    }

    @Override
    public Long likes(Long id) {
        return (long) courseLikeRepository.getByCourseId(id).size();
    }

    @Override
    public Boolean favorite(CourseFavoriteDTO courseFavoriteDTO) {
        CourseFavorite favorite = courseFavoriteRepository.isFavorite(courseFavoriteDTO.getCourseId(), courseFavoriteDTO.getUserId());
        if (isFavorite(courseFavoriteDTO)){
            courseFavoriteRepository.delete(favorite.getId());
            return false;
        }
        courseFavoriteRepository.save(courseFavoriteDTO);
        return true;
    }

    @Override
    public Boolean isFavorite(CourseFavoriteDTO courseFavoriteDTO) {
        CourseFavorite favorite = courseFavoriteRepository.isFavorite(courseFavoriteDTO.getCourseId(), courseFavoriteDTO.getUserId());
        return favorite != null && favorite.getId() != null;
    }

    @Override
    public Long favorites(Long id) {
        return (long) courseFavoriteRepository.getByCourseId(id).size();
    }
}
