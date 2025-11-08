package com.livenne.service;

import com.livenne.common.model.UserCourse;

import java.util.List;

public interface UserCourseService {
    UserCourse add(UserCourse userCourse);
    void delete(Long id);
    void update(UserCourse userCourse);
    UserCourse get(Long id);
    List<UserCourse> getListByUserId(Long userId);
}
