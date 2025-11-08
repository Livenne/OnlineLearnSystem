package com.livenne.repository.impl;

import com.livenne.common.model.Course;
import com.livenne.repository.CourseRepository;

import java.util.List;

public class CourseRepositoryImpl extends BaseRepository implements CourseRepository {
    public static CourseRepository instance = new CourseRepositoryImpl();

    @Override
    public Course save(Course course) {
        try {
            entityManager.getTransaction().begin();
            Course merge = entityManager.merge(course);
            entityManager.getTransaction().commit();
            return merge;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Course findById(Long id) {
        try {
            return entityManager.find(Course.class, id);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Course findByName(String name) {
        try {
            return (Course) entityManager.createQuery(
                    "select c from Course c where c.name=:name"
            ).setParameter("name",name).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Course> findAll() {
        try {
            return entityManager.createQuery("from Course", Course.class).getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Course course) {
        save(course);
    }
}
