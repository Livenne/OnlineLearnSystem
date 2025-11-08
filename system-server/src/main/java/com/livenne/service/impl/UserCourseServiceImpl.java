package com.livenne.service.impl;

import com.livenne.common.model.UserCourse;
import com.livenne.repository.UserCourseRepository;
import com.livenne.repository.impl.UserCourseRepositoryImpl;
import com.livenne.service.UserCourseService;

import java.util.List;

public class UserCourseServiceImpl implements UserCourseService {

    public static UserCourseService instance = new UserCourseServiceImpl();
    private final UserCourseRepository userCourseRepository = UserCourseRepositoryImpl.instance;

    @Override
    public UserCourse add(UserCourse userCourse) {
        return userCourseRepository.save(userCourse);
    }

    @Override
    public void delete(Long id) {
        userCourseRepository.delete(id);
    }

    @Override
    public void update(UserCourse userCourse) {
        userCourseRepository.update(userCourse);
    }

    @Override
    public UserCourse get(Long id) {
        return userCourseRepository.findById(id);
    }

    @Override
    public List<UserCourse> getListByUserId(Long userId) {
        return userCourseRepository.findAll(userId);
    }
}
