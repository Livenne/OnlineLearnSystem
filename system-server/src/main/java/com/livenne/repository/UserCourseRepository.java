package com.livenne.repository;

import com.livenne.common.model.UserCourse;

import java.util.List;

public interface UserCourseRepository {
    UserCourse findById(Long id);
    UserCourse save(UserCourse userCourse);
    void delete(Long id);
    void update(UserCourse userCourse);
    List<UserCourse> findAll(Long userId);
}
