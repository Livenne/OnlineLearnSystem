package com.livenne.repository.impl;

import com.livenne.common.model.UserCourse;
import com.livenne.repository.UserCourseRepository;

import java.util.List;

public class UserCourseRepositoryImpl extends BaseRepository implements UserCourseRepository {

    public static UserCourseRepository instance = new UserCourseRepositoryImpl();

    @Override
    public UserCourse findById(Long id) {
        try {
            return entityManager.find(UserCourse.class, id);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserCourse save(UserCourse userCourse) {
        try {
            entityManager.getTransaction().begin();
            UserCourse merge = entityManager.merge(userCourse);
            entityManager.getTransaction().commit();
            return merge;
        }catch (Exception e) {
            return  null;
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(UserCourse userCourse) {
        save(userCourse);
    }

    @Override
    public List<UserCourse> findAll(Long userId) {
        try {
            return entityManager.createQuery("from UserCourse where userId=:userId", UserCourse.class)
                    .setParameter("userId", userId).getResultList();
        }catch (Exception e) {
            return null;
        }
    }
}
