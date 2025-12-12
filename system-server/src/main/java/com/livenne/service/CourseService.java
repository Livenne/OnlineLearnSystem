package com.livenne.service;

import com.livenne.common.model.dto.CourseCommentDTO;
import com.livenne.common.model.dto.CourseFavoriteDTO;
import com.livenne.common.model.dto.CourseLikeDTO;
import com.livenne.common.model.entity.Chapter;
import com.livenne.common.model.entity.Course;
import com.livenne.common.model.entity.CourseComment;

import java.util.List;

public interface CourseService {
    Boolean isExistById(Long id);
    Course getById(Long id);
    List<Course> getAll();
    List<Course> getByIdList(List<Long> idList);
    List<Course> getByUserId(Long userId);
    List<Course> queryByName(String name);

    List<Chapter> getChapter(Long id);
    Long chapters(Long id);

    Long purchase(Long id);

    Long rating(Long id);

    List<CourseComment> getComment(Long id);
    Long comments(Long id);
    Long comment(Long courseId,CourseCommentDTO courseCommentDTO);

    Boolean like(CourseLikeDTO courseLikeDTO);

    Boolean isLike(CourseLikeDTO courseLikeDTO);
    Long likes(Long id);

    Boolean favorite(CourseFavoriteDTO courseFavoriteDTO);

    Boolean isFavorite(CourseFavoriteDTO courseFavoriteDTO);
    Long favorites(Long id);

}
