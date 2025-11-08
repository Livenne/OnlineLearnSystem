package com.livenne.service.impl;

import com.livenne.common.model.CourseItem;
import com.livenne.repository.CourseItemRepository;
import com.livenne.repository.impl.CourseItemRepositoryImpl;
import com.livenne.service.CourseItemService;

import java.util.List;

public class CourseItemServiceImpl implements CourseItemService {

    public static CourseItemService instance = new CourseItemServiceImpl();

    private final CourseItemRepository courseItemRepository = CourseItemRepositoryImpl.instance;

    @Override
    public CourseItem add(CourseItem courseItem) {
        return courseItemRepository.save(courseItem);
    }

    @Override
    public void update(CourseItem courseItem) {
        courseItemRepository.update(courseItem);
    }

    @Override
    public void delete(Long itemId) {
        courseItemRepository.delete(itemId);
    }

    @Override
    public CourseItem get(Long itemId) {
        return courseItemRepository.findById(itemId);
    }

    @Override
    public List<CourseItem> getListByCourseId(Long courseId) {
        return courseItemRepository.findAll(courseId);
    }
}
