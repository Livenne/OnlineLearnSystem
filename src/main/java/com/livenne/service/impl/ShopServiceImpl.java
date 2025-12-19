package com.livenne.service.impl;

import com.livenne.repository.UserCourseRepository;
import com.livenne.repository.UserRepository;
import com.livenne.service.CourseService;
import com.livenne.service.ShopService;
import io.github.livenne.annotation.container.Autowired;
import io.github.livenne.annotation.container.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private UserCourseRepository userCourseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseService courseService;

    @Override
    public Boolean buy(Long userId, Long courseId) {
        Long price = courseService.getById(courseId).getPrice();
        Long score = userRepository.getById(userId).getScore();
        long diff = score - price;
        if (diff < 0) return false;
        userRepository.updateScore(userId,diff);
        return true;
    }

    @Override
    public Boolean buy(Long userId, List<Long> courseIdList) {
        courseIdList.forEach(id->buy(userId,id));
        return true;
    }
}
