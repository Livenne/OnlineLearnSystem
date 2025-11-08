package com.livenne.repository.impl;

import com.livenne.common.model.CourseItem;
import com.livenne.repository.CourseItemRepository;

import java.util.List;

public class CourseItemRepositoryImpl extends BaseRepository implements CourseItemRepository {

    public static CourseItemRepository instance = new CourseItemRepositoryImpl();

    @Override
    public CourseItem save(CourseItem courseItem) {
        try{
            entityManager.getTransaction().begin();
            CourseItem merge = entityManager.merge(courseItem);
            entityManager.getTransaction().commit();
            return merge;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public CourseItem findById(Long itemId) {
        try {
            return entityManager.find(CourseItem.class, itemId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<CourseItem> findAll(Long courseId) {
        try{
            return entityManager.createQuery("from CourseItem where courseId=:courseId",CourseItem.class)
                    .setParameter("courseId", courseId)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(Long itemId) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(itemId));
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(CourseItem courseItem) {
        save(courseItem);
    }
}
